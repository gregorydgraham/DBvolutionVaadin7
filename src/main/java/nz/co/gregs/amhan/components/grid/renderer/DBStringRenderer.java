/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBStringLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBString;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBStringRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBString, String> {

	public DBStringRenderer(R row, DBString fieldOfThisRow) {
		super(new DBStringLabelGenerator<R>(row, fieldOfThisRow));
	}

}
