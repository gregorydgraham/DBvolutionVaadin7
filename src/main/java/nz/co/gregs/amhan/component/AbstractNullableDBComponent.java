/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.component;

import com.vaadin.flow.component.AbstractCompositeField;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 * @param <S> the simple type of the QueryableDatatype
 * @param <T> the simple type of the Component
 * @param <Q> the QDT to use
 * @param <C> the component to use
 */
public abstract class AbstractNullableDBComponent<S, T, Q extends QueryableDatatype<S>, C extends Component & HasValueAndElement<AbstractField.ComponentValueChangeEvent<C, T>, T>> extends AbstractCompositeField<VerticalLayout, AbstractNullableDBComponent<S, T, Q, C>, T> {

	private final Checkbox enabler = new Checkbox(false);
	private T previousValue = null;
	private final Q queryableDatatype;
	private final C input = getComponent();

	public AbstractNullableDBComponent(Q qdt, String label, T defaultValue) {
		super(defaultValue);
		this.queryableDatatype = qdt;
		setLabel(label);

		if (queryableDatatype.isDefined() && queryableDatatype.isNotNull()) {
			final T val = convertDBValueToComponentValue(queryableDatatype.getValue());
			setModelValue(val, false);
			enabler.setValue(true);
			input.setValue(val);
		}

		input.addValueChangeListener((event) -> {
			queryableDatatype.setValue(convertComponentValueToDBValue(event.getValue()));
			setModelValue(event.getValue(), true);
		});

		enabler.addValueChangeListener((event) -> {
			toggleInputField(event);
			if (!event.getValue()) {
				queryableDatatype.setValueToNull();
			}
			setModelValue(getValue(), true);
		});

		enabler.getStyle().set("padding", "0").set("margin", "0").set("border", "0");

		input.setEnabled(enabler.getValue());

		final VerticalLayout content = getContent();
		content.add(enabler, input);
		content.addClassName("dbvolution-optional-value");
		content.setAlignItems(FlexComponent.Alignment.START);
	}

	@Override
	protected void setPresentationValue(T newPresentationValue) {
		input.setValue(newPresentationValue);
	}

	@SuppressWarnings("unchecked")
	abstract protected T convertDBValueToComponentValue(S value);

	@SuppressWarnings("unchecked")
	abstract protected S convertComponentValueToDBValue(T value);

	private void toggleInputField(AbstractField.ComponentValueChangeEvent<Checkbox, Boolean> event) {
		final Boolean value = event.getValue();
		if (value) {
			input.setValue(previousValue);
		} else {
			previousValue = getValue();
			input.setValue(input.getEmptyValue());
		}
		input.setEnabled(value);
	}

	@Override
	public T getValue() {
		if (enabler.getValue()) {
			return input.getValue();
		} else {
			return getEmptyValue();
		}
	}

	@Override
	public void setValue(T value) {
		super.setValue(value);
		if (value == null) {
			enabler.setValue(Boolean.FALSE);
		} else {
			enabler.setValue(Boolean.TRUE);
		}
	}

	public void setDefaultValue(T suggestion) {
		this.previousValue = suggestion;
	}

	protected abstract C getComponent();

	public final void setLabel(String label) {
		enabler.setLabel(label);
	}
}