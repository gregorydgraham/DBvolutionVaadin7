/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.dbvolutionvaadin7.component;

import com.vaadin.flow.component.AbstractCompositeField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import java.time.LocalDate;
import java.util.List;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBDate;
import nz.co.gregs.dbvolution.datatypes.DBInteger;
import nz.co.gregs.dbvolution.datatypes.DBNumber;
import nz.co.gregs.dbvolution.datatypes.DBString;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBRowForm<R extends DBRow> extends AbstractCompositeField<FormLayout, DBRowForm<R>, R> {

	public DBRowForm(R row) {
		super(DBRow.copyDBRow(row));
		setValue(DBRow.copyDBRow(row));
		setPresentationValue(DBRow.copyDBRow(row));
	}

	@Override
	protected final void setPresentationValue(R row) {
		final FormLayout content = getContent();
		content.removeAll();
		List<PropertyWrapper> columns = row.getColumnPropertyWrappers();
		for (PropertyWrapper column : columns) {
			QueryableDatatype<?> qdt = column.getQueryableDatatype();
			Component comp = new TextField(column.javaName());
			if (qdt instanceof DBInteger) {
				final DBIntegerNullableComponent com = new DBIntegerNullableComponent((DBInteger) qdt, column.javaName(), "");
				com.addValueChangeListener((event) -> {
					setValue(row);
				});
				comp = com;
			} else if (qdt instanceof DBNumber) {
				final DBNumberNullableComponent com = new DBNumberNullableComponent((DBNumber) qdt, column.javaName(), "");
				com.addValueChangeListener((event) -> {
					setValue(row);
				});
				comp = com;
			} else if (qdt instanceof DBString) {
				final DBStringNullableComponent com = new DBStringNullableComponent((DBString) qdt, column.javaName(), "");
				com.addValueChangeListener((event) -> {
					setValue(row);
				});
				comp = com;
			} else if (qdt instanceof DBDate) {
				final DBDateNullableComponent com = new DBDateNullableComponent((DBDate) qdt, column.javaName(), LocalDate.now());
				com.addValueChangeListener((event) -> {
					setValue(row);
				});
				comp = com;
			}
			content.add(comp);
		}
	}

	@Override
	public final void setValue(R value) {
		super.setValue(DBRow.copyDBRow(value));
	}

	@Override
	protected boolean valueEquals(R value1, R value2) {
		return value1.toString().equals(value2.toString());
	}

	public class DBRowValueChangeEvent<R extends DBRow> extends com.vaadin.flow.component.ComponentEvent<DBRowForm<R>>// ValueChangeEvent<DBRowForm<R>> {
	{

		public DBRowValueChangeEvent(DBRowForm<R> source, boolean fromClient) {
			super(source, fromClient);
		}
	}
}
