/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Div;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.*;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 * @param <BASETYPE>
 * @param <QDT>
 */
public abstract class QueryableDatatypeField<ROW extends DBRow, BASETYPE, QDT extends QueryableDatatype<BASETYPE>>
		extends AbstractCompositeField<Div, QueryableDatatypeField<ROW, BASETYPE, QDT>, BASETYPE>
		implements HasLabel, HasOrderedComponents, QDTUpdateNotifier<BASETYPE, QDT> {

	@SuppressWarnings("unchecked")
	public static <ROW extends DBRow, T, QDT extends QueryableDatatype<T>> QueryableDatatypeField<ROW, T, QDT> getFieldForRowAndQDT(ROW example, QDT qdt) {
		QueryableDatatypeField<?, ?, ?> returnField;
		if (qdt instanceof DBBoolean) {
			returnField = DBBooleanField.getField(example, (DBBoolean) qdt);
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
			returnField = DBInstantField.getField(example, (DBInstant) qdt);
		} else if (qdt instanceof DBInteger) {
			returnField = DBIntegerField.getField(example, (DBInteger) qdt);
//		} else if (qdt instanceof DBIntegerEnum) {
//			return new DBIntegerEnumField(example, (DBIntegerEnum) qdt);
//		} else if (qdt instanceof DBJavaObject) {
//			return new DBJavaObjectField(example, (DBJavaObject) qdt);
//		} else if (qdt instanceof DBLargeBinary) {
//			return new DBLargeBinaryField<A>(example, (DBLargeBinary) qdt);
		} else if (qdt instanceof DBLocalDate) {
			returnField = new DBLocalDateField<>(example, (DBLocalDate) qdt);
//			DBLocalDate localQDT = (DBLocalDate) qdt;
//			returnField = (C) new DatePicker(
//					prop.javaName(),
//					new QDTValueChangeListener(localQDT)
//			);
		} else if (qdt instanceof DBLocalDateTime) {
			returnField = new DBLocalDateTimeField<>(example, (DBLocalDateTime) qdt);
//		} else if (qdt instanceof DBNumberStatistics) {
//			return new DBNumberStatisticsField<A>(example, (DBNumberStatistics) qdt);
		} else if (qdt instanceof DBPasswordHash) {
			returnField = new DBPasswordHashField<>(example, (DBPasswordHash) qdt);
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
		} else if (qdt instanceof DBStringTrimmed) {
			returnField = new DBStringTrimmedField<>(example, (DBStringTrimmed) qdt);
		} else if (qdt instanceof DBUUID) {
			returnField = new DBUUIDField<>(example, (DBUUID) qdt);
		} else if (qdt instanceof DBUnknownDatatype) {
			returnField = new DBUnknownDatatypeField<>(example, (DBUnknownDatatype) qdt);
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
		} else if (qdt instanceof DBNumber) {
			returnField = new DBNumberField<>(example, (DBNumber) qdt);
		} else if (qdt instanceof DBString) {
			returnField = new DBStringField<>(example, (DBString) qdt);
		} // and a default handler for all those ones I've forgotten
		else {
			returnField = new DBTodoField<>(example, (QueryableDatatype) qdt);
		}
		return (QueryableDatatypeField<ROW, T, QDT>) returnField;
	}

	private final ROW row;
	private final QDT qdt;
	private final PropertyWrapper<ROW, BASETYPE, QDT> prop;

	protected QueryableDatatypeField(BASETYPE defaultValue, ROW row, QDT qdt) {
		super(defaultValue);
		this.row = row;
		this.qdt = qdt;
		this.prop = this.row.getPropertyWrapperOf(qdt);
		setLabel(prop.javaName());
		if (prop.isPrimaryKey()) {
			setReadOnly(true);
		}
	}

	public QDT getQueryableDatatype() {
		return qdt;
	}

	public ROW getRow() {
		return row;
	}

//	@Override
//	public void setValue(BASETYPE value) {
//		System.out.println("QueryableDatatypeField.setValue() - SET VALUE: " + value);
//		super.setValue(value);
//		tellObserversOfSetValueEvent();
//	}

	protected void updateQDT(ComponentValueChangeEvent<?, BASETYPE> e) {
		System.out.println("QueryableDatatypeField.updateQDT(EVENT) - SET VALUE: " + e.getValue());
		getQueryableDatatype().setValue(e.getValue());
		tellObserversOfSetValueEvent();
	}

	protected void updateQDT(BASETYPE newValue) {
		System.out.println("QueryableDatatypeField.updateQDT(BASETYPE,BASETYPE) - SET VALUE: " + newValue);
		getQueryableDatatype().setValue(newValue);
		tellObserversOfSetValueEvent();
	}

	private void tellObserversOfSetValueEvent() {
		fireEvent(new QDTUpdateNotifier.Event<>(this));
	}

}
