/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import nz.co.gregs.amhan.browser.setter.QueryableDatatypeSetter;
import nz.co.gregs.amhan.browser.valueprovider.QueryableDatatypeValueProvider;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 * @param <TYPE>
 */
public class QDTComponents<ROW extends DBRow, TYPE> {

	private final String columnName;
	private final String propertyName;
	private final QueryableDatatypeField<ROW, TYPE, ?> editor;
	private final QueryableDatatypeValueProvider<ROW, TYPE, ? extends QueryableDatatype<TYPE>> valueProvider;
	private final QueryableDatatypeSetter<ROW, TYPE, ? extends QueryableDatatype<TYPE>> setter;

	@SuppressWarnings(value = "unchecked")
	public static <R extends DBRow, T, Q extends QueryableDatatype<T>> QDTComponents<R, T> getFor(R row, Q field) {
		QDTComponents qdtComponents = new QDTComponents(row, field);
		final QueryableDatatypeField editor = qdtComponents.getEditor();
		return qdtComponents;
	}

	private <QDT extends QueryableDatatype<TYPE>> QDTComponents(ROW row, QDT field) {
		editor = QueryableDatatypeField.getFieldForRowAndQDT(row, field);
		valueProvider = QueryableDatatypeValueProvider.getValueProvider(row, field);
		setter = QueryableDatatypeSetter.getSetter(row, field);
		PropertyWrapper<?, ?, ?> property = row.getPropertyWrapperOf(field);
		columnName = property.columnName();
		propertyName = property.javaName();
	}

	public QueryableDatatypeField<ROW, TYPE, ?> getEditor() {
		return editor;
	}

	public QueryableDatatypeValueProvider<ROW, TYPE, ? extends QueryableDatatype<TYPE>> getValueProvider() {
		return valueProvider;
	}

	public QueryableDatatypeSetter<ROW, TYPE, ? extends QueryableDatatype<TYPE>> getSetter() {
		return setter;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getColumnName() {
		return columnName;
	}

}
