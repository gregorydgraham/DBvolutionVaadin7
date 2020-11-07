/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.renderer;

import nz.co.gregs.amhan.browser.grid.labelgenerator.DBUnknownDatatypeLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBUnknownDatatype;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBUnknownDatatypeRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBUnknownDatatype, Object> {
	
	public DBUnknownDatatypeRenderer(R row, DBUnknownDatatype fieldOfThisRow) {
		super(new DBUnknownDatatypeLabelGenerator<R>(row, fieldOfThisRow));
	}
	
}
