/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLargeObject;

/**
 *
 * @author gregorygraham
 * @param <A>
 * @param <T>
 */
public class DBLargeObjectLabelGenerator<A extends DBRow, T> extends AbstractDBRowPropertyLabelGenerator<A, DBLargeObject<T>> {

	public DBLargeObjectLabelGenerator(A example, DBLargeObject<T> qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		return getQDT(item).stringValue();
	}

}
