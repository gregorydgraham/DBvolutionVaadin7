/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.renderer;

import nz.co.gregs.amhan.browser.grid.labelgenerator.DBIntegerEnumLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBEnumValue;
import nz.co.gregs.dbvolution.datatypes.DBIntegerEnum;

/**
 *
 * @author gregorygraham
 * @param <R>
 * @param <E>
 */
public class DBIntegerEnumRenderer<R extends DBRow, E extends Enum<E> & DBEnumValue<Long>> extends AbstractDBRowPropertyRenderer<R, DBIntegerEnum<E>, Long> {

	public DBIntegerEnumRenderer(R example, DBIntegerEnum<E> fieldOfExample) {
		super(new DBIntegerEnumLabelGenerator<R,E>(example,fieldOfExample));
	}

}
