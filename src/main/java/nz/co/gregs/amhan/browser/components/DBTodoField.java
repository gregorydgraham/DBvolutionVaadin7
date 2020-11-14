/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.textfield.TextField;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBTodoField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Object, QueryableDatatype<Object>> {

	TextField field;

	public DBTodoField(ROW row, QueryableDatatype<Object> qdt) {
		super("", row, qdt);
	}

	@Override
	protected void setPresentationValue(Object newPresentationValue) {
		field.setValue(newPresentationValue != null ? newPresentationValue.toString() : null);
	}

	@Override
	protected void addInternalComponents(QueryableDatatype<Object> qdt) {
		add(field);
	}

	@Override
	protected void createInternalComponents() {
		field = new TextField();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		field.addValueChangeListener(e -> updateQDT(e.getValue()));
	}

}
