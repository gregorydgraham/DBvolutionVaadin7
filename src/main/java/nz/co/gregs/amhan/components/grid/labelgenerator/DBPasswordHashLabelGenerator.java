/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBPasswordHash;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 * @param <A>
 */
public class DBPasswordHashLabelGenerator<A extends DBRow> extends AbstractDBRowPropertyLabelGenerator<A, DBPasswordHash> {

	public DBPasswordHashLabelGenerator(A example, DBPasswordHash qdt) {
		super(example, qdt);
	}

	@Override
	@SuppressWarnings("ReplaceAllDot")
	public String apply(A item) {
		final QueryableDatatype<?> value = getQDT(item);
		return value.isNull() ? "" : value.toString().replaceAll(".", "*");
	}

}
