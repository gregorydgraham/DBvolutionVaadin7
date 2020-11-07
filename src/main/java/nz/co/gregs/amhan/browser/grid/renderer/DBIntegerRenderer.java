/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.renderer;

import nz.co.gregs.amhan.browser.grid.labelgenerator.DBIntegerLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBInteger;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBIntegerRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBInteger, Long> {

	public DBIntegerRenderer(R example, DBInteger fieldOfExample) {
		super(new DBIntegerLabelGenerator<R>(example, fieldOfExample));
	}

}
