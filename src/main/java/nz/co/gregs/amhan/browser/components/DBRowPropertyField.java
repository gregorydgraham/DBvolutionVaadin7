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
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.*;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 */
class DBRowPropertyField {

	@SuppressWarnings("unchecked")
	private static <A extends DBRow, T, QDT extends QueryableDatatype<T>, C extends AbstractField<C, T>> C getFieldForQDT(A example, QDT qdt) {
		PropertyWrapper<T, QDT> prop = example.getPropertyWrapperOf(qdt);
		C returnField= null;
//		if (qdt instanceof DBBoolean) {
//			return new DBBoolean<A>(example, (DBBoolean) qdt);
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
//		} else 
		if (qdt instanceof DBInstant) {
			DBInstant stringQDT = (DBInstant) qdt;
			final DBInstantField dateTimePicker = new DBInstantField(
					stringQDT.stringValue(),
					new QDTValueChangeListener(stringQDT)
			);
			returnField= (C) dateTimePicker;
		} else if (qdt instanceof DBInteger) {
			DBInteger integerQDT = (DBInteger) qdt;
			final DBIntegerField numberField = new DBIntegerField(
					prop.javaName(),
					new QDTValueChangeListener(integerQDT)
			);
			returnField= (C) numberField;
//			return new DBIntegerField<A>(example, (DBInteger) qdt);
//		} else if (qdt instanceof DBIntegerEnum) {
//			return new DBIntegerEnumField(example, (DBIntegerEnum) qdt);
//		} else if (qdt instanceof DBJavaObject) {
//			return new DBJavaObjectField(example, (DBJavaObject) qdt);
//		} else if (qdt instanceof DBLargeBinary) {
//			return new DBLargeBinaryField<A>(example, (DBLargeBinary) qdt);
		} else if (qdt instanceof DBLocalDate) {
			DBLocalDate localQDT = (DBLocalDate) qdt;
			returnField= (C) new DatePicker(
					prop.javaName(),
					new QDTValueChangeListener(localQDT)
			);
		} else if (qdt instanceof DBLocalDateTime) {
			DBLocalDateTime localQDT = (DBLocalDateTime) qdt;
			returnField= (C) new DateTimePicker(
					prop.javaName(),
					new QDTValueChangeListener(localQDT)
			);
//		} else if (qdt instanceof DBNumberStatistics) {
//			return new DBNumberStatisticsField<A>(example, (DBNumberStatistics) qdt);
		} else if (qdt instanceof DBPasswordHash) {
			DBPasswordHash stringQDT = (DBPasswordHash) qdt;
			returnField= (C) new DBStringField(
					prop.javaName(),
					new QDTValueChangeListener(stringQDT)
			);
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
			DBString stringQDT = (DBString) qdt;
			returnField= (C) new DBStringField(
					prop.javaName(),
					new QDTValueChangeListener(stringQDT)
			);
//		} // and a default handler for all those ones I've forgotten
//		else {
//			return new DefaultDBRowPropertyField(example, qdt);
		}
		if (prop.isPrimaryKey()){
			returnField.setReadOnly(true);
		}
		return returnField;
	}

	static <T> AbstractField<?, T> getEditor(DBRow example, QueryableDatatype<T> qdt) {
		return getFieldForQDT(example, qdt);
	}

	static class QDTValueChangeListener<F extends AbstractField<F, A>, A> implements HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<F, A>> {

		private final QueryableDatatype<A> qdt;

		public QDTValueChangeListener(QueryableDatatype<A> queryabledatatype) {
			this.qdt = queryabledatatype;
		}

		@Override
		public void valueChanged(AbstractField.ComponentValueChangeEvent<F, A> a) {
			qdt.setValue(a.getValue());
		}
	}
}
