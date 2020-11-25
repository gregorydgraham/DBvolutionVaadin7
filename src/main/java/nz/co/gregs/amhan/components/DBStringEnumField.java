/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.select.Select;
import java.util.List;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBEnumValue;
import nz.co.gregs.dbvolution.datatypes.DBStringEnum;

/**
 * Server-side component for the {@code vaadin-number-field} element.
 *
 * @author Vaadin Ltd.
 * @param <ROW>
 * @param <ENUM>
 */
public class DBStringEnumField<ROW extends DBRow, ENUM extends Enum<ENUM> & DBEnumValue<String>> extends QueryableDatatypeField<ROW, String, DBStringEnum<ENUM>> {

	Select<String> field;

	public DBStringEnumField(ROW row, DBStringEnum<ENUM> qdt) {
		super("", row, qdt);
	}

	@Override
	protected final void setPresentationValue(String newPresentationValue) {
		if (newPresentationValue != null) {
			field.setValue(getQueryableDatatype().getEnumFromCode(newPresentationValue).name());
		} else {
			field.clear();
		}
	}

	@Override
	protected void addInternalComponents(DBStringEnum<ENUM> qdt) {
		add(field);
	}

	@Override
	protected void createInternalComponents() {
		field = new Select<String>();
		final List<String> validCodes = getQueryableDatatype().getValidNames();
		field.setItems(validCodes);
	}

	@Override
	protected void addInternalValueChangeListeners() {
		field.addValueChangeListener(new ValueChangeListener<AbstractField.ComponentValueChangeEvent<Select<String>, String>>() {
			@Override
			public void valueChanged(AbstractField.ComponentValueChangeEvent<Select<String>, String> e) {
				updateQDT(getQueryableDatatype().getEnumFromName(e.getValue()).getCode());
			}
		});
	}
}
