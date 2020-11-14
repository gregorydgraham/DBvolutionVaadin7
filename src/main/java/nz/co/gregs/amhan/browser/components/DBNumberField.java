/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.textfield.NumberField;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBNumber;

/**
 * Server-side component for the {@code vaadin-number-field} element.
 *
 * @author Vaadin Ltd.
 * @param <ROW>
 */
public class DBNumberField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Number, DBNumber> {

	NumberField field;

	public DBNumberField(ROW row, DBNumber qdt) {
		super(0D, row, qdt);
	}

	@Override
	protected final void setPresentationValue(Number newPresentationValue) {
		field.setValue(newPresentationValue != null ? newPresentationValue.doubleValue() : null);
	}

	@Override
	protected void addInternalComponents(DBNumber qdt) {
		add(field);
	}

	@Override
	protected void createInternalComponents() {
		field = new NumberField();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		field.addValueChangeListener(e -> updateQDT(e.getValue()));
	}

}
