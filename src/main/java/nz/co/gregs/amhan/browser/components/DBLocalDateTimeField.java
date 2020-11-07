/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import java.time.LocalDateTime;
import nz.co.gregs.dbvolution.datatypes.DBLocalDateTime;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 */
public class DBLocalDateTimeField extends DateTimePicker{

	private DBLocalDateTimeField(String label, ValueChangeListener<ComponentValueChangeEvent<DateTimePicker, LocalDateTime>> listener) {
		super(label, listener);
	}
	
	public static DBLocalDateTimeField getField(PropertyWrapper<LocalDateTime, DBLocalDateTime> prop){
		return new DBLocalDateTimeField(prop.javaName(),new QDTValueChangeListener<>(prop.getQueryableDatatype()));
	}
	
}
