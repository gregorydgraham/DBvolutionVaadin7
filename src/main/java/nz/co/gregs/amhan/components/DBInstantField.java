/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.timepicker.TimePicker;
import java.time.*;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBInstant;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBInstantField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Instant, DBInstant> {

	DatePicker datePicker;
	TimePicker timePicker;

	public DBInstantField(ROW row, DBInstant qdt) {
		super(null, row, qdt);
	}

	@Override
	protected final void setPresentationValue(Instant newPresentationValue) {
		if (newPresentationValue != null) {
			final LocalDateTime localdatetime = convertToLocalDateTime(newPresentationValue);
			datePicker.setValue(localdatetime.toLocalDate());
			timePicker.setValue(localdatetime.toLocalTime());
		} else {
			datePicker.clear();
			timePicker.clear();
		}
	}

	@Override
	protected void addInternalComponents(DBInstant qdt) {
		add(new Span(datePicker, timePicker));
	}

	@Override
	protected void createInternalComponents() {
		datePicker = new DatePicker();
		timePicker = new TimePicker();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		datePicker.addValueChangeListener(e -> updateDate(e));
		timePicker.addValueChangeListener(e -> updateTime(e));
	}

	private void updateDate(AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate> e) {
		updateInstant(e);
	}

	private void updateTime(AbstractField.ComponentValueChangeEvent<TimePicker, LocalTime> e) {
		updateInstant(e);
	}

	private void updateInstant(AbstractField.ComponentValueChangeEvent<?, ?> e) {
		LocalDateTime localdatetime = LocalDateTime.of(datePicker.getValue(), timePicker.getValue());
		Instant instant = convertToInstant(localdatetime);
		if (instant == null && instant != getValue()) {
			updateQDT(instant);
		} else if (instant != null && !instant.equals(getValue())) {
			updateQDT(instant);
		}
	}

	private Instant convertToInstant(LocalDateTime value) {
		return value == null ? null : value.atZone(ZoneId.systemDefault()).toInstant();
	}

	private LocalDateTime convertToLocalDateTime(Instant value) {
		return value == null ? null : value.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}
