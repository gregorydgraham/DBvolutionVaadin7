/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.checkbox.Checkbox;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBBoolean;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBBooleanField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Boolean, DBBoolean> {

	Checkbox checkBox = new Checkbox();

	public static <ROW extends DBRow> DBBooleanField<ROW> getField(ROW row, DBBoolean qdt) {
		return new DBBooleanField<>(row, qdt);
	}

	private DBBooleanField(ROW row, DBBoolean qdt) {
		super(Boolean.FALSE, row, qdt);
		setPresentationValue(qdt.getValue());
		checkBox.setLabel(getLabel());
		checkBox.addValueChangeListener(event -> updateQDT(event));
		add(checkBox);
	}

	@Override
	protected final void setPresentationValue(Boolean newPresentationValue) {
		checkBox.setValue(newPresentationValue);
	}

}
