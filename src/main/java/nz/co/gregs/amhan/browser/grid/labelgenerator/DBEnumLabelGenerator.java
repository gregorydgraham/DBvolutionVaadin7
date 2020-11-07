/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBEnum;
import nz.co.gregs.dbvolution.datatypes.DBEnumValue;

/**
 *
 * @author gregorygraham
 * @param <A>
 * @param <E>
 * @param <T>
 */
public class DBEnumLabelGenerator<A extends DBRow, E extends Enum<E> & DBEnumValue<T>, T> extends AbstractDBRowPropertyLabelGenerator<A, DBEnum<E, T>> {

	public DBEnumLabelGenerator(A example, DBEnum<E, T> qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		return getQDT(item).stringValue();
	}

}
