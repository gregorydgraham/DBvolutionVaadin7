/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.textfield.TextField;
import nz.co.gregs.dbvolution.datatypes.DBStringTrimmed;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 */
public class DBStringTrimmedField extends TextField {
	
	/**
	 * Constructs an empty {@code TextField} with a label and a value change
	 * listener.
	 * @param label the text to set as the label
	 * @param listener the value change listener
	 * @see #setLabel(String)
	 * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
	 */
	public DBStringTrimmedField(String label, ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> listener) {
		super(label, listener);
	}
	
	public static DBStringTrimmedField getField(PropertyWrapper<String, DBStringTrimmed> prop){
		return new DBStringTrimmedField(prop.javaName(),new QDTValueChangeListener<>(prop.getQueryableDatatype()));
	}
	
}
