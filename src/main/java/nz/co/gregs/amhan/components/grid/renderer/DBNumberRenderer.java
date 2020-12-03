/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBNumberLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBNumber;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBNumberRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBNumber, Number> {

	public DBNumberRenderer(R example, DBNumber fieldOfExample) {
		super(new DBNumberLabelGenerator<R>(example, fieldOfExample));
	}

}
