/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLargeText;

/**
 *
 * @author gregorygraham
 * @param <A>
 */
public class DBLargeTextLabelGenerator<A extends DBRow> extends AbstractDBRowPropertyLabelGenerator<A,DBLargeText> {

	public DBLargeTextLabelGenerator(A example, DBLargeText qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		return getQDT(item).stringValue();
	}

}
