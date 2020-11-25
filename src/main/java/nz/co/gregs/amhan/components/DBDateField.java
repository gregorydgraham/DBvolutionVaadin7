/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBDate;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBDateField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Date, DBDate> {

	DateTimePicker picker;

	public DBDateField(ROW row, DBDate qdt) {
		super(new Date(), row, qdt);
	}

	@Override
	protected final void setPresentationValue(Date newPresentationValue) {
		picker.setValue(LocalDateTime.ofInstant(newPresentationValue.toInstant(), ZoneId.systemDefault()));
	}

	@Override
	protected void addInternalComponents(DBDate qdt) {
		add(picker);
	}

	@Override
	protected void createInternalComponents() {
		picker = new DateTimePicker();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		picker.addValueChangeListener(e -> updateDBDate(e));
	}

	private void updateDBDate(AbstractField.ComponentValueChangeEvent<DateTimePicker, LocalDateTime> e) {
		LocalDateTime time = e.getValue();
		Instant instant = time.atZone(ZoneId.systemDefault()).toInstant();
		final Date date = Date.from(instant);
		updateQDT(date);
	}
}
