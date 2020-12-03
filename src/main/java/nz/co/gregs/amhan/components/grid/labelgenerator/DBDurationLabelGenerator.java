/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBDuration;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBDurationLabelGenerator<R extends DBRow> extends AbstractDBRowPropertyLabelGenerator<R, DBDuration> {

	public DBDurationLabelGenerator(R example, DBDuration qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(R item) {
		return getQDT(item).stringValue();
	}

}
