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

	TextField field ;

	public DBStringField(ROW row, DBString qdt) {
		super("", row, qdt);
	}

	@Override
	protected void setPresentationValue(String newPresentationValue) {
		field.setValue(newPresentationValue.isEmpty() ? "" : newPresentationValue);
	}

	@Override
	protected void addInternalComponents(DBString qdt) {
		add(field);
	}

	@Override
	protected void createInternalComponents() {
		field= new TextField();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		field.addValueChangeListener(e -> updateQDT(e));
	}

}
