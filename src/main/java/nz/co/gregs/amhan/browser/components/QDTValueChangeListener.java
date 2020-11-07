/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 */
class QDTValueChangeListener<F extends AbstractField<F, A>, A> implements HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<F, A>> {
	
	private final QueryableDatatype<A> qdt;

	public QDTValueChangeListener(QueryableDatatype<A> queryabledatatype) {
		this.qdt = queryabledatatype;
	}

	@Override
	public void valueChanged(AbstractField.ComponentValueChangeEvent<F, A> a) {
		qdt.setValue(a.getValue());
	}
	
}
