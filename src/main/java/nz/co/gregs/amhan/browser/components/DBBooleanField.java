/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.checkbox.Checkbox;
import nz.co.gregs.dbvolution.datatypes.DBBoolean;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 */
public class DBBooleanField extends Checkbox{

	
	public static DBBooleanField getField(PropertyWrapper<Boolean, DBBoolean>prop){
		return new DBBooleanField(prop.javaName(),new QDTValueChangeListener<>(prop.getQueryableDatatype()));
	}

	private DBBooleanField(String label, ValueChangeListener<ComponentValueChangeEvent<Checkbox, Boolean>> listener) {
		super(label, listener);
	}
	
}
