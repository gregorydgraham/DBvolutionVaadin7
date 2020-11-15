/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.checkbox.Checkbox;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBBoolean;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBBooleanField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Boolean, DBBoolean> {

	Checkbox checkBox;

	public DBBooleanField(ROW row, DBBoolean qdt) {
		super(Boolean.FALSE, row, qdt);
	}

	@Override
	protected final void setPresentationValue(Boolean newPresentationValue) {
		checkBox.setValue(newPresentationValue);
	}

	@Override
	protected void addInternalComponents(DBBoolean qdt) {
		add(checkBox);
	}

	@Override
	protected void createInternalComponents() {
		checkBox = new Checkbox();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		checkBox.addValueChangeListener(event -> updateQDT(event));
	}

}
