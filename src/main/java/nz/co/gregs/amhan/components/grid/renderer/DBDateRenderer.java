/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import java.util.Date;
import nz.co.gregs.amhan.components.grid.labelgenerator.DBDateLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBDate;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBDateRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBDate, Date> {

	public DBDateRenderer(R example, DBDate fieldOfExample) {
		super(new DBDateLabelGenerator<R>(example, fieldOfExample));
	}

}
