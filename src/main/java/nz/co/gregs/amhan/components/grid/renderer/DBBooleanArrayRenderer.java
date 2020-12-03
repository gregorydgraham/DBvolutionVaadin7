/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBBooleanArrayLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBBooleanArray;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBBooleanArrayRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBBooleanArray, Boolean[]> {
	
	public DBBooleanArrayRenderer(R example, DBBooleanArray fieldOfExample) {
		super(new DBBooleanArrayLabelGenerator<R>(example,fieldOfExample));
	}
	
}
