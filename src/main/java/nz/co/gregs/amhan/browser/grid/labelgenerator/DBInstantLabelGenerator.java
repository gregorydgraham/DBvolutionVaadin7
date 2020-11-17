/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.labelgenerator;

import java.time.Instant;
import java.time.ZoneId;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBInstant;

/**
 *
 * @author gregorygraham
 * @param <A>
 */
public class DBInstantLabelGenerator<A extends DBRow> extends AbstractDBRowPropertyLabelGenerator<A, DBInstant> {

	public DBInstantLabelGenerator(A example, DBInstant qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		final Instant value = getQDT(item).getValue();
		if (value == null) {
			return "";
		} else {
			return value.atZone(ZoneId.systemDefault()).toString();
		}
	}

}
