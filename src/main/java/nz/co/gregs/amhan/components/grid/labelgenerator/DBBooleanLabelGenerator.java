/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBBoolean;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 * @param <A>
 */
public class DBBooleanLabelGenerator<A extends DBRow> extends AbstractDBRowPropertyLabelGenerator<A, DBBoolean> {

	public DBBooleanLabelGenerator(A example, DBBoolean qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		final QueryableDatatype<?> value = getQDT(item);
		if (value.isNull()) {
			return "";
		} else {
			if (value instanceof DBBoolean) {
				final DBBoolean booleanValue = (DBBoolean) value;
				return booleanValue.booleanValue().toString();
			} else {
				return value.stringValue();
			}
		}
	}

}
