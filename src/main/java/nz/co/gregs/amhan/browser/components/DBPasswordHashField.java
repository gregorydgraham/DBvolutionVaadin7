/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.textfield.TextField;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBPasswordHash;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBPasswordHashField<ROW extends DBRow> extends QueryableDatatypeField<ROW, String, DBPasswordHash> {

	TextField field ;

	public DBPasswordHashField(ROW row, DBPasswordHash qdt) {
		super("", row, qdt);
	}

	@Override
	protected final void setPresentationValue(String newPresentationValue) {
		field.setValue(newPresentationValue);
	}

	@Override
	protected void addInternalComponents(DBPasswordHash qdt) {
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
