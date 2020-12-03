/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import java.time.Duration;
import nz.co.gregs.amhan.components.grid.labelgenerator.DBDurationLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBDuration;

/**
 *
 * @author gregorygraham
 * @param <A>
 */
public class DBDurationRenderer<A extends DBRow> extends AbstractDBRowPropertyRenderer<A, DBDuration, Duration> {

	public DBDurationRenderer(A example, DBDuration fieldOfExample) {
		super(new DBDurationLabelGenerator<A>(example, fieldOfExample));
	}

}
