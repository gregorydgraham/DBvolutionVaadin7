/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.dbvolutionvaadin7.component;

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
 * @param <T>
 * @param <Q>
 * @param <C>
 */
public abstract class AbstractNullableDBComponent<S, T, Q extends QueryableDatatype<S>, C extends Component & HasValueAndElement<AbstractField.ComponentValueChangeEvent<C, T>, T>> extends AbstractCompositeField<VerticalLayout, AbstractNullableDBComponent<S, T, Q, C>, T> {

	private final Checkbox enabler = new Checkbox(false);
	private T previousValue = null;
	private final C input = getComponent();

	public AbstractNullableDBComponent(T defaultValue) {
		super(defaultValue);

		input.addValueChangeListener((event) -> {
			setModelValue(getValue(), true);
		});

		enabler.addValueChangeListener((event) -> {
			toggleInputField(event);
			setModelValue(getValue(), true);
		});

		enabler.getStyle().set("padding", "0").set("margin", "0").set("border", "0");

		input.setEnabled(enabler.getValue());

		final VerticalLayout content = getContent();
		content.add(enabler, input);
		content.addClassName("optional-date-picker");
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
			input.setValue(null);
		}
		input.setEnabled(value);
	}

	@Override
	public T getValue() {
		if (enabler.getValue()) {
			return getValue();
		} else {
			return null;
		}
	}

	@Override
	public void setValue(T value) {
		if (value == null) {
			enabler.setValue(Boolean.FALSE);
		} else {
			enabler.setValue(Boolean.TRUE);
		}
		super.setValue(value);
	}

	public void setDefaultValue(T suggestion) {
		this.previousValue = suggestion;
	}

	public abstract C getComponent();
	public abstract C getComponentForQDT(Q qdt);
}
