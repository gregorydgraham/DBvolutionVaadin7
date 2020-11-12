/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.HasValue;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 * @param <BASETYPE>
 */
@Deprecated
public class QDTValueChangeListener<BASETYPE> implements HasValue.ValueChangeListener<HasValue.ValueChangeEvent<BASETYPE>>{

	private final QueryableDatatype<BASETYPE> qdt;

	public QDTValueChangeListener(QueryableDatatype<BASETYPE> qdt) {
		this.qdt = qdt;
	}

	@Override
	public void valueChanged(HasValue.ValueChangeEvent<BASETYPE> event) {
		System.out.println("QDTValueChangeListener - SET QDT VALUE TO: "+event.getValue());
		qdt.setValue(event.getValue());
	}
	
}
