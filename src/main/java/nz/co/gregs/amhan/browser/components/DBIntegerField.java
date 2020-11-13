/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.textfield.IntegerField;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBInteger;

/**
 * Server-side component for the {@code vaadin-number-field} element.
 *
 * @author Vaadin Ltd.
 * @param <ROW>
 */
public class DBIntegerField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Long, DBInteger> {

	static <ROW extends DBRow> QueryableDatatypeField<ROW, Long, DBInteger> getField(ROW example, DBInteger dbInteger) {
		return new DBIntegerField<>(example, dbInteger);
	}

	IntegerField field = new IntegerField();

	public DBIntegerField(ROW row, DBInteger qdt) {
		super(0L, row, qdt);
		setPresentationValue(qdt.getValue());
		field.setLabel(getLabel());
		field.addValueChangeListener(
				e -> updateQDT(e.getValue() == null ? null : e.getValue().longValue())
		);
		add(field);
	}

	@Override
	protected final void setPresentationValue(Long newPresentationValue) {
		if (newPresentationValue != null) {
			field.setValue(newPresentationValue.intValue());
		} else {
			field.clear();
		}
	}
}
