/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBPasswordHashLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBPasswordHash;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBPasswordHashRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBPasswordHash, String> {

	public DBPasswordHashRenderer(R example, DBPasswordHash fieldOfExample) {
		super(new DBPasswordHashLabelGenerator<R>(example, fieldOfExample));
	}

}
