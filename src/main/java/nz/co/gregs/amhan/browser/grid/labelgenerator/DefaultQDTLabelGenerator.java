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
 * @param <ROW>
 * @param <TYPE>
 */
public class DefaultQDTLabelGenerator<ROW extends DBRow, TYPE> extends AbstractDBRowPropertyLabelGenerator<ROW, QueryableDatatype<TYPE>> {

	public DefaultQDTLabelGenerator(ROW example, QueryableDatatype<TYPE> qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(ROW item) {
		return getQDT(item).stringValue();
	}

}
