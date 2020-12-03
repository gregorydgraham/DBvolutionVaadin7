/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBUntypedValueLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBUntypedValue;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBUntypedValueRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBUntypedValue, Object> {
	
	public DBUntypedValueRenderer(R row, DBUntypedValue fieldOfThisRow) {
		super(new DBUntypedValueLabelGenerator<R>(row, fieldOfThisRow));
	}
	
}
