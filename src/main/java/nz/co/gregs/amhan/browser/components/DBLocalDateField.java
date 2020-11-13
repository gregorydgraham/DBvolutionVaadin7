/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.datepicker.DatePicker;
import java.time.LocalDate;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLocalDate;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBLocalDateField<ROW extends DBRow> extends QueryableDatatypeField<ROW, LocalDate, DBLocalDate> {

	DatePicker picker = new DatePicker();

	public DBLocalDateField(ROW row, DBLocalDate qdt) {
		super(LocalDate.now(), row, qdt);
		setPresentationValue(qdt.getValue());
		picker.setLabel(getLabel());
		picker.addValueChangeListener(e->updateQDT(e));
		add(picker);
	}

	@Override
	protected final void setPresentationValue(LocalDate newPresentationValue) {
		picker.setValue(newPresentationValue);
	}

}
