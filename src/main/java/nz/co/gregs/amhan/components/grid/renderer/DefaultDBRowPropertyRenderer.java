/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DefaultQDTLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DefaultDBRowPropertyRenderer<R extends DBRow, T> extends AbstractDBRowPropertyRenderer<R, QueryableDatatype<T>, T> {

	public DefaultDBRowPropertyRenderer(R row, QueryableDatatype<T> fieldOfThisRow) {
		super(new DefaultQDTLabelGenerator<R, T>(row, fieldOfThisRow));
	}

}
