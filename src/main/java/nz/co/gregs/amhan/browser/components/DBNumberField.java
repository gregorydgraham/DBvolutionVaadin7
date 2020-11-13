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

	NumberField field = new NumberField();

	public DBNumberField(ROW row, DBNumber qdt) {
		super(0D, row, qdt);
		setPresentationValue(qdt.getValue());
		field.setLabel(getLabel());
		field.addValueChangeListener(e -> updateQDT(e.getValue()));
		add(field);
	}

	@Override
	protected final void setPresentationValue(Number newPresentationValue) {
		field.setValue(newPresentationValue != null ? newPresentationValue.doubleValue() : null);
	}

}
