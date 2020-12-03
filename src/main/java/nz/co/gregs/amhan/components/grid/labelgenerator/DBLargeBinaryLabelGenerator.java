/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLargeBinary;

/**
 *
 * @author gregorygraham
 * @param <A>
 */
public class DBLargeBinaryLabelGenerator<A extends DBRow> extends AbstractDBRowPropertyLabelGenerator<A, DBLargeBinary> {

	public DBLargeBinaryLabelGenerator(A example, DBLargeBinary qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		return getQDT(item).getSizeAsReadableString();
	}

}
