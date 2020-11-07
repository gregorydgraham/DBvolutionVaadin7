/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import nz.co.gregs.amhan.browser.components.DBIntegerField;
import nz.co.gregs.amhan.browser.components.DBInstantField;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.*;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 */
class QueryableDatatypeField {

	@SuppressWarnings("unchecked")
	private static <A extends DBRow, T, QDT extends QueryableDatatype<T>, C extends AbstractField<C, T>> C getFieldForQDT(A example, QDT qdt) {
		PropertyWrapper<T, QDT> prop = example.getPropertyWrapperOf(qdt);
		C returnField = null;
		if (qdt instanceof DBBoolean) {
			returnField = (C) DBBooleanField.getField((PropertyWrapper<Boolean, DBBoolean>) prop);
//		} else if (qdt instanceof DBBooleanArray) {
//			return new DBBooleanArrayField<A>(example, (DBBooleanArray) qdt);
//		} else if (qdt instanceof DBDateOnly) {
//			return new DBDateOnlyField<A>(example, (DBDateOnly) qdt);
//		} else if (qdt instanceof DBDateRepeat) {
//			return new DBDateRepeatField<A>(example, (DBDateRepeat) qdt);
//		} else if (qdt instanceof DBDuration) {
//			return new DBDurationField<A>(example, (DBDuration) qdt);
//		} else if (qdt instanceof DBEncryptedText) {
//			return new DBEncryptedTextField<A>(example, (DBEncryptedText) qdt);
		} else if (qdt instanceof DBInstant) {
			returnField = (C) DBInstantField.getField((PropertyWrapper<Instant, DBInstant>) prop);
		} else if (qdt instanceof DBInteger) {
			returnField = (C) DBIntegerField.getField((PropertyWrapper<Long, DBInteger>) prop);
//		} else if (qdt instanceof DBIntegerEnum) {
//			return new DBIntegerEnumField(example, (DBIntegerEnum) qdt);
//		} else if (qdt instanceof DBJavaObject) {
//			return new DBJavaObjectField(example, (DBJavaObject) qdt);
//		} else if (qdt instanceof DBLargeBinary) {
//			return new DBLargeBinaryField<A>(example, (DBLargeBinary) qdt);
		} else if (qdt instanceof DBLocalDate) {
			returnField = (C) DBLocalDateField.getField((PropertyWrapper<LocalDate, DBLocalDate>) prop);
//			DBLocalDate localQDT = (DBLocalDate) qdt;
//			returnField = (C) new DatePicker(
//					prop.javaName(),
//					new QDTValueChangeListener(localQDT)
//			);
		} else if (qdt instanceof DBLocalDateTime) {
			returnField = (C) DBLocalDateTimeField.getField((PropertyWrapper<LocalDateTime, DBLocalDateTime>) prop);
//		} else if (qdt instanceof DBNumberStatistics) {
//			return new DBNumberStatisticsField<A>(example, (DBNumberStatistics) qdt);
		} else if (qdt instanceof DBPasswordHash) {
			returnField = (C) DBStringField.getField((PropertyWrapper<String, DBString>) prop);
//			return new DBPasswordHashField<A>(example, (DBPasswordHash) qdt);
//		} else if (qdt instanceof DBStatistics) {
//			return new DBStatisticsField(example, (DBStatistics) qdt);
//		} else 
//		if (qdt instanceof DBStringEnum) {
//			return new TextField(
//					prop.javaName(), 
//					qdt.getValue(), 
//					(field,newValue)->{((DBStringEnum<Enum<E>>) qdt).setValue(newLiteralValue);}
//			);
//			return new DBStringEnumField(example, (DBStringEnum) qdt);
//		} else if (qdt instanceof DBStringTrimmed) {
//			return new DBStringTrimmedField<A>(example, (DBStringTrimmed) qdt);
//		} else if (qdt instanceof DBUUID) {
//			return new DBUUIDField<A>(example, (DBUUID) qdt);
//		} else if (qdt instanceof DBUnknownDatatype) {
//			return new DBUnknownDatatypeField<A>(example, (DBUnknownDatatype) qdt);
//		} else if (qdt instanceof DBUntypedValue) {
//			return new DBUntypedValueField<A>(example, (DBUntypedValue) qdt);
//		} // the following types need to be checked last as they have subtypes 
//		else if (qdt instanceof DBDate) {
//			return new DBDateField<A>(example, (DBDate) qdt);
//		} else if (qdt instanceof DBEnum) {
//			return new DBEnumField(example, (DBEnum) qdt);
//		} else if (qdt instanceof DBLargeText) {
//			return new DBLargeTextField<A>(example, (DBLargeText) qdt);
//		} else if (qdt instanceof DBLargeObject) {
//			return new DBLargeObjectField(example, (DBLargeObject) qdt);
//		} else if (qdt instanceof DBNumber) {
//			return new DBNumberField<A>(example, (DBNumber) qdt);
		} else //		if (qdt instanceof DBString) 
		{
			returnField = (C) DBStringField.getField((PropertyWrapper<String, DBString>) prop);
//		} // and a default handler for all those ones I've forgotten
//		else {
//			return new DefaultDBRowPropertyField(example, qdt);
		}
		if (prop.isPrimaryKey()) {
			returnField.setReadOnly(true);
		}
		return returnField;
	}

	static <T> AbstractField<?, T> getField(DBRow example, QueryableDatatype<T> qdt) {
		return getFieldForQDT(example, qdt);
	}
}
