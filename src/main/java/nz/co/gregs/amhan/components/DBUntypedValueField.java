/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.textfield.TextField;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBUntypedValue;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBUntypedValueField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Object, DBUntypedValue> {

	TextField field ;

	public DBUntypedValueField(ROW row, DBUntypedValue qdt) {
		super("", row, qdt);
		setReadOnly(true);
	}

	@Override
	protected void setPresentationValue(Object newPresentationValue) {
		field.setValue(newPresentationValue==null ? "" : newPresentationValue.toString());
	}

	@Override
	protected void addInternalComponents(DBUntypedValue qdt) {
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
