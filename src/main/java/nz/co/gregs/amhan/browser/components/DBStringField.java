/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.textfield.TextField;

/**
 *
 * @author gregorygraham
 */
public class DBStringField extends TextField {
	
	/**
	 * Constructs an empty {@code TextField}.
	 */
	public DBStringField() {
		super();
	}

	/**
	 * Constructs an empty {@code TextField} with the given label.
	 * @param label the text to set as the label
	 */
	public DBStringField(String label) {
		super(label);
	}

	/**
	 * Constructs an empty {@code TextField} with the given label and
	 * placeholder text.
	 * @param label the text to set as the label
	 * @param placeholder the placeholder text to set
	 */
	public DBStringField(String label, String placeholder) {
		super(label, placeholder);
	}

	/**
	 * Constructs a {@code TextField} with the given label, an initial value and
	 * placeholder text.
	 * @param label the text to set as the label
	 * @param initialValue the initial value
	 * @param placeholder the placeholder text to set
	 * @see #setValue(Object)
	 * @see #setPlaceholder(String)
	 */
	public DBStringField(String label, String initialValue, String placeholder) {
		super(label, initialValue==null?"":initialValue, placeholder);
	}

	/**
	 * Constructs an empty {@code TextField} with a value change listener.
	 * @param listener the value change listener
	 * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
	 */
	public DBStringField(ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> listener) {
		super(listener);
	}

	/**
	 * Constructs an empty {@code TextField} with a label and a value change
	 * listener.
	 * @param label the text to set as the label
	 * @param listener the value change listener
	 * @see #setLabel(String)
	 * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
	 */
	public DBStringField(String label, ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> listener) {
		super(label, listener);
	}

	/**
	 * Constructs an empty {@code TextField} with a label,a value change
	 * listener and an initial value.
	 * @param label the text to set as the label
	 * @param initialValue the initial value
	 * @param listener the value change listener
	 * @see #setLabel(String)
	 * @see #setValue(Object)
	 * @see #addValueChangeListener(com.vaadin.flow.component.HasValue.ValueChangeListener)
	 */
	public DBStringField(String label, String initialValue, ValueChangeListener<? super ComponentValueChangeEvent<TextField, String>> listener) {
		super(label, initialValue==null?"":initialValue, listener);
	}
	
}
