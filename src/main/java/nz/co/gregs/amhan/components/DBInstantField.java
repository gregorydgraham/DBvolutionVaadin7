/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.shared.Registration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBInstant;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBInstantField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Instant, DBInstant> {

	DateTimePicker picker;

	public DBInstantField(ROW row, DBInstant qdt) {
		super(null, row, qdt);
	}

	@Override
	protected final void setPresentationValue(Instant newPresentationValue) {
		if (newPresentationValue != null) {
			picker.setValue(convertToLocalDateTime(newPresentationValue));
		} else {
			picker.clear();
		}
	}

	@Override
	protected void addInternalComponents(DBInstant qdt) {
		add(picker);
	}

	@Override
	protected void createInternalComponents() {
		picker = new DateTimePicker();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		picker.addValueChangeListener(e -> updateInstant(e));
	}

	private void updateInstant(AbstractField.ComponentValueChangeEvent<DateTimePicker, LocalDateTime> e) {
		final Instant instant = convertToInstant(e.getValue());
		updateQDT(instant);
	}

	private Instant convertToInstant(LocalDateTime value) {
		return value == null ? null : value.toInstant(ZoneOffset.UTC);
	}

	private LocalDateTime convertToLocalDateTime(Instant value) {
		return value == null ? null : value.atZone(ZoneOffset.UTC).toLocalDateTime();
	}
}
