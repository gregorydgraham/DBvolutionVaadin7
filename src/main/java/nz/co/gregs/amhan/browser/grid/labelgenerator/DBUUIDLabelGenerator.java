/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBUUID;

/**
 *
 * @author gregorygraham
 * @param <A>
 */
public class DBUUIDLabelGenerator<A extends DBRow> extends AbstractDBRowPropertyLabelGenerator<A, DBUUID> {

	public DBUUIDLabelGenerator(A example, DBUUID qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		return getQDT(item).stringValue();
	}

}
