/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.component;

import com.vaadin.flow.component.datepicker.DatePicker;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import nz.co.gregs.dbvolution.datatypes.DBDate;

public class DBDateNullableComponent extends AbstractNullableDBComponent<Date, LocalDate, DBDate, DatePicker> {

	public DBDateNullableComponent(DBDate qdt, String label, LocalDate defaultValue) {
		super(qdt, label, defaultValue);
	}

	@Override
	public DatePicker getComponent() {
		final DatePicker text = new DatePicker();
		return text;
	}

	@Override
	public void setValue(LocalDate value) {
		super.setValue(value);
	}

	@Override
	protected LocalDate convertDBValueToComponentValue(Date value) {
		return value == null ? null : Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	protected Date convertComponentValueToDBValue(LocalDateTime value) {
		return value == null ? null : Date.from(value.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDateTime asLocalDate(Date date) {
		return date == null ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	@Override
	protected Date convertComponentValueToDBValue(LocalDate value) {
		return value==null?null:convertComponentValueToDBValue(value.atTime(8, 0));
	}	
}
