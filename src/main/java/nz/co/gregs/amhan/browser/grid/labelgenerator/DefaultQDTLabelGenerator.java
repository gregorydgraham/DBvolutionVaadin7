/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 * @param <A>
 * @param <T>
 */
public class DefaultQDTLabelGenerator<A extends DBRow, T> extends AbstractDBRowPropertyLabelGenerator<A, QueryableDatatype<T>> {

	public DefaultQDTLabelGenerator(A example, QueryableDatatype<T> qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		return getQDT(item).stringValue();
	}

}
