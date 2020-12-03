/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBEnumValue;
import nz.co.gregs.dbvolution.datatypes.DBIntegerEnum;

/**
 *
 * @author gregorygraham
 * @param <A>
 * @param <E>
 */
public class DBIntegerEnumLabelGenerator<A extends DBRow, E extends Enum<E> & DBEnumValue<Long>> extends AbstractDBRowPropertyLabelGenerator<A, DBIntegerEnum<E>> {

	public DBIntegerEnumLabelGenerator(A example, DBIntegerEnum<E> qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		return getQDT(item).stringValue();
	}

}
