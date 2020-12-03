/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBDateRepeatLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBDateRepeat;
import org.joda.time.Period;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBDateRepeatRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBDateRepeat, Period> {

	public DBDateRepeatRenderer(R example, DBDateRepeat fieldOfExample) {
		super(new DBDateRepeatLabelGenerator<R>(example, fieldOfExample));
	}

}
