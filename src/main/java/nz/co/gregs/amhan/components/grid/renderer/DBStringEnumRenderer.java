/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBStringEnumLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBEnumValue;
import nz.co.gregs.dbvolution.datatypes.DBStringEnum;

/**
 *
 * @author gregorygraham
 * @param <R>
 * @param <E>
 */
public class DBStringEnumRenderer<R extends DBRow, E extends Enum<E> & DBEnumValue<String>> extends AbstractDBRowPropertyRenderer<R, DBStringEnum<E>, String> {

	public DBStringEnumRenderer(R row, DBStringEnum<E> fieldOfThisRow) {
		super(new DBStringEnumLabelGenerator<R, E>(row, fieldOfThisRow));
	}

}
