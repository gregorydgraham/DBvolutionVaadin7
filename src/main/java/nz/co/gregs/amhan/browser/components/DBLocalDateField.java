/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.datepicker.DatePicker;
import java.time.LocalDate;
import nz.co.gregs.dbvolution.datatypes.DBLocalDate;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 */
public class DBLocalDateField extends DatePicker{

	private DBLocalDateField(String label, ValueChangeListener<ComponentValueChangeEvent<DatePicker, LocalDate>> listener) {
		super(label, listener);
	}
	
	public static DBLocalDateField getField(PropertyWrapper<LocalDate, DBLocalDate> prop){
		return new DBLocalDateField(prop.javaName(),new QDTValueChangeListener<>(prop.getQueryableDatatype()));
	}
	
}
