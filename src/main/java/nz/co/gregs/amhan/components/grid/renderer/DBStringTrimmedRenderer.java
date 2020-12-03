/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBStringTrimmedLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBStringTrimmed;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBStringTrimmedRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBStringTrimmed, String> {

	public DBStringTrimmedRenderer(R row, DBStringTrimmed fieldOfThisRow) {
		super(new DBStringTrimmedLabelGenerator<R>(row, fieldOfThisRow));
	}

}
