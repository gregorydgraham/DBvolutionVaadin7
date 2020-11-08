/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.textfield.TextField;
import nz.co.gregs.dbvolution.datatypes.DBPasswordHash;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 */
public class DBPasswordHashField extends TextField {
	
	/**
	 * Constructs an empty {@code TextField} with a label and a value change
	 * listener.
	 * @param label the text to set as the label
	 * @param listener the value change listener
	 * @see #setLabel(String)
	 * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
	 */
	public DBPasswordHashField(String label, ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> listener) {
		super(label, listener);
	}
	
	public static DBPasswordHashField getField(PropertyWrapper<String, DBPasswordHash> prop){
		return new DBPasswordHashField(prop.javaName(),new QDTValueChangeListener<>(prop.getQueryableDatatype()));
	}
	
}
