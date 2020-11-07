/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.renderer;

import nz.co.gregs.amhan.browser.grid.labelgenerator.DBEnumLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBEnum;
import nz.co.gregs.dbvolution.datatypes.DBEnumValue;

/**
 *
 * @author gregorygraham
 * @param <R>
 * @param <E>
 * @param <T>
 */
public class DBEnumRenderer<R extends DBRow, E extends Enum<E> & DBEnumValue<T>, T> extends AbstractDBRowPropertyRenderer<R, DBEnum<E, T>, T> {

	public DBEnumRenderer(R example, DBEnum<E, T> fieldOfExample) {
		super(new DBEnumLabelGenerator<R, E, T>(example, fieldOfExample));
	}

}
