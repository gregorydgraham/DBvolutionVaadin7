/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import java.time.Instant;
import nz.co.gregs.amhan.components.grid.labelgenerator.DBInstantLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBInstant;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBInstantRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBInstant, Instant> {

	public DBInstantRenderer(R example, DBInstant fieldOfExample) {
		super(new DBInstantLabelGenerator<R>(example,fieldOfExample));
	}

}
