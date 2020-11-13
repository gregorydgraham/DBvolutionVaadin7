/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.textfield.TextField;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBString;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBStringField<ROW extends DBRow> extends QueryableDatatypeField<ROW, String, DBString> {

	TextField field = new TextField();

	public DBStringField(ROW row, DBString qdt) {
		super("", row, qdt);
		setPresentationValue(qdt.getValue());
		field.setLabel(getLabel());
		field.addValueChangeListener(e -> updateQDT(e));
		add(field);
	}

	@Override
	protected void setPresentationValue(String newPresentationValue) {
		field.setValue(newPresentationValue.isEmpty()?"":newPresentationValue);
	}

}
