/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBBooleanLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBBoolean;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBBooleanRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBBoolean, Boolean> {
	
	public DBBooleanRenderer(R example, DBBoolean fieldOfExample) {
		super(new DBBooleanLabelGenerator<R>(example,fieldOfExample));
	}
	
}
