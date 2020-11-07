/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.renderer;

import nz.co.gregs.amhan.browser.grid.labelgenerator.DBLargeBinaryLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLargeBinary;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBLargeBinaryRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBLargeBinary, byte[]> {

	public DBLargeBinaryRenderer(R example, DBLargeBinary fieldOfExample) {
		super(new DBLargeBinaryLabelGenerator<R>(example, fieldOfExample));
	}

}
