/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBEnumValue;
import nz.co.gregs.dbvolution.datatypes.DBStringEnum;

/**
 *
 * @author gregorygraham
 * @param <A>
 */
public class DBStringEnumLabelGenerator<A extends DBRow,E extends Enum<E> & DBEnumValue<String>> extends AbstractDBRowPropertyLabelGenerator<A, DBStringEnum<E>> {

	public DBStringEnumLabelGenerator(A example, DBStringEnum<E> qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		return getQDT(item).stringValue();
	}

}
