/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import java.util.function.Function;
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
			returnField = new DBBooleanField<>(example, (DBBoolean) qdt);
		} else if (qdt instanceof DBBooleanArray) {
			returnField = new DBBooleanArrayField(example, (DBBooleanArray) qdt);
		} else if (qdt instanceof DBDateOnly) {
			return new DBDateOnlyField(example, (DBDateOnly) qdt);
//		} else if (qdt instanceof DBDateRepeat) {
//			return new DBDateRepeatField<A>(example, (DBDateRepeat) qdt);
//		} else if (qdt instanceof DBDuration) {
//			return new DBDurationField<A>(example, (DBDuration) qdt);
		} else if (qdt instanceof DBEncryptedText) {
			return new DBEncryptedTextField(example, (DBEncryptedText) qdt);
		} else if (qdt instanceof DBInstant) {
			returnField = new DBInstantField(example, (DBInstant) qdt);
		} else if (qdt instanceof DBInteger) {
			returnField = new DBIntegerField(example, (DBInteger) qdt);
		} else if (qdt instanceof DBIntegerEnum) {
			return new DBIntegerEnumField(example, (DBIntegerEnum) qdt);
//		} else if (qdt instanceof DBJavaObject) {
//			return new DBJavaObjectField(example, (DBJavaObject) qdt);
//		} else if (qdt instanceof DBLargeBinary) {
//			return new DBLargeBinaryField<A>(example, (DBLargeBinary) qdt);
		} else if (qdt instanceof DBLocalDate) {
			returnField = new DBLocalDateField<>(example, (DBLocalDate) qdt);
		} else if (qdt instanceof DBLocalDateTime) {
			returnField = new DBLocalDateTimeField<>(example, (DBLocalDateTime) qdt);
//		} else if (qdt instanceof DBNumberStatistics) {
//			return new DBNumberStatisticsField<A>(example, (DBNumberStatistics) qdt);
		} else if (qdt instanceof DBPasswordHash) {
			returnField = new DBPasswordHashField<>(example, (DBPasswordHash) qdt);
//		} else if (qdt instanceof DBStatistics) {
//			return new DBStatisticsField(example, (DBStatistics) qdt);		
		} else if (qdt instanceof DBStringEnum) {
			return new DBStringEnumField(example, (DBStringEnum) qdt);
		} else if (qdt instanceof DBStringTrimmed) {
			returnField = new DBStringTrimmedField<>(example, (DBStringTrimmed) qdt);
		} else if (qdt instanceof DBUUID) {
			returnField = new DBUUIDField<>(example, (DBUUID) qdt);
		} else if (qdt instanceof DBUnknownDatatype) {
			returnField = new DBUnknownDatatypeField<>(example, (DBUnknownDatatype) qdt);
//		} else if (qdt instanceof DBUntypedValue) {
//			return new DBUntypedValueField<A>(example, (DBUntypedValue) qdt);
		} // the following types need to be checked last as they have subtypes 
		else if (qdt instanceof DBDate) {
			returnField = new DBDateField<>(example, (DBDate) qdt);
		} else if (qdt instanceof DBEnum) {
			return new DBEnumField(example, (DBEnum) qdt);
		} else if (qdt instanceof DBLargeText) {
			returnField =new  DBLargeTextField(example, (DBLargeText) qdt);
		} else if (qdt instanceof DBLargeBinary) {
			final DBLargeBinaryField dbLargeBinaryField = new DBLargeBinaryField(example, (DBLargeBinary) qdt);
			returnField= dbLargeBinaryField;
		} else if (qdt instanceof DBNumber) {
			returnField = new DBNumberField<>(example, (DBNumber) qdt);
		} else if (qdt instanceof DBString) {
			returnField = new DBStringField<>(example, (DBString) qdt);
		} // and a default handler for all those ones I've forgotten
		else {
			return new DBTodoField<>(example, (QueryableDatatype) qdt);
		}
		return (QueryableDatatypeField<ROW, T, QDT>) returnField;
	}

	private final ROW row;
	private final QDT qdt;
	private final PropertyWrapper<ROW, BASETYPE, QDT> prop;
	private boolean initialised;

	protected QueryableDatatypeField(BASETYPE defaultValue, ROW row, QDT qdt) {
		super(defaultValue);
		this.row = row;
		this.qdt = qdt;
		this.prop = this.row.getPropertyWrapperOf(qdt);
		initComponents(qdt);
	}

	protected final void initComponents(QDT qdt) {
		initLabel();
		createInternalComponents();
		addInternalComponents(qdt);
		setPresentationValue(qdt.getValue());
		if (prop.isPrimaryKey()) {
			setReadOnly(true);
		}
		addInternalValueChangeListeners();
	}

	Label labelForQDTField;

	protected void initLabel() {
		labelForQDTField = new Label();
		labelForQDTField.setText(prop.javaName());
		labelForQDTField.setTitle(prop.javaName());
		add(new Div(labelForQDTField));
	}

	protected final void updateQDT(ComponentValueChangeEvent<?, BASETYPE> e) {
		System.out.println("QueryableDatatypeField.updateQDT(EVENT) - SET VALUE: " + e.getValue());
		updateQDT(e.getValue());
	}

	protected final void updateQDT(BASETYPE newValue) {
		setValue(newValue);
		tellObserversOfSetValueEvent();
	}

	protected final <SOMETYPE> void updateQDT(SOMETYPE newValue, Function<SOMETYPE,BASETYPE> ifNotNullTransform) {
		if (newValue==null){
			updateQDTToNull();
		}else{
			updateQDT(ifNotNullTransform.apply(newValue));
		}
	}

	protected final void updateQDTToNull() {
		setValue(null);
		tellObserversOfSetValueEvent();
	}

	private void tellObserversOfSetValueEvent() {
		fireEvent(new QDTUpdateNotifier.Event<>(this, qdt));
	}

	public PropertyWrapper<ROW, BASETYPE, QDT> getPropertyWrapper() {
		return prop;
	}

	public synchronized final void reloadValue() {
		setPresentationValue(qdt.getValue());
	}

	@Override
	public BASETYPE getValue() {
		return super.getValue();
	}

	@Override
	public void setValue(BASETYPE value) {
		qdt.setValue(value);
		super.setValue(value);
	}

	public QDT getQueryableDatatype() {
		return qdt;
	}

	protected abstract void createInternalComponents();

	protected abstract void addInternalComponents(QDT qdt);

	protected abstract void addInternalValueChangeListeners();
}
