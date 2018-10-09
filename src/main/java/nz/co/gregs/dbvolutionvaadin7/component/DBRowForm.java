/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.dbvolutionvaadin7.component;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.internal.AbstractFieldSupport;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
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
public class DBRowForm<R extends DBRow> extends FormLayout {

	public DBRowForm(R row) {
		List<PropertyWrapper> columns = row.getColumnPropertyWrappers();
		for (PropertyWrapper column : columns) {
			QueryableDatatype<?> qdt = column.getQueryableDatatype();
			Component comp = new TextField(column.javaName());
			if (qdt instanceof DBInteger) {
				comp = new DBIntegerNullableComponent((DBInteger) qdt, column.javaName(), "");
			} else if (qdt instanceof DBNumber) {
				comp = new DBNumberNullableComponent((DBNumber) qdt, column.javaName(), "");
			} else if (qdt instanceof DBString) {
				comp = new DBStringNullableComponent((DBString) qdt, column.javaName(), "");
			} else if (qdt instanceof DBDate) {
				final DBDateNullableComponent dateComp = new DBDateNullableComponent((DBDate) qdt, column.javaName(), LocalDate.now());
				dateComp.addValueChangeListener((event) -> {
				});
				comp = dateComp;
			}
			add(comp);
		}
	}

}
