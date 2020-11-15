/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.textfield.*;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBUnknownDatatype;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBUnknownDatatypeField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Object, DBUnknownDatatype> {

	private TextField textField;

	public DBUnknownDatatypeField(ROW row, DBUnknownDatatype qdt) {
		super("Unknown", row, qdt);
	}

	@Override
	protected final void setPresentationValue(Object newPresentationValue) {
		if (newPresentationValue != null) {
			textField.setValue(newPresentationValue.toString());
		} else {
			textField.clear();
		}
	}

	@Override
	protected void addInternalComponents(DBUnknownDatatype qdt) {
		add(textField);
	}

	@Override
	protected void createInternalComponents() {
		textField = new TextField();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		textField.addValueChangeListener(e -> updateQDT(e.getValue()));
	}
}
