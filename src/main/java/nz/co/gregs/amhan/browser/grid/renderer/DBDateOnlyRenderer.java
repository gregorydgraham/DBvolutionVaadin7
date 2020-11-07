/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.renderer;

import java.util.Date;
import nz.co.gregs.amhan.browser.grid.labelgenerator.DBDateOnlyLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBDateOnly;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBDateOnlyRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBDateOnly, Date> {

	public DBDateOnlyRenderer(R example, DBDateOnly fieldOfExample) {
		super(new DBDateOnlyLabelGenerator<R>(example, fieldOfExample));
	}

}
