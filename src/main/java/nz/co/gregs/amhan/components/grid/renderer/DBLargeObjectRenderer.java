/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBLargeObjectLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLargeObject;

/**
 *
 * @author gregorygraham
 * @param <R>
 * @param <T>
 */
public class DBLargeObjectRenderer<R extends DBRow, T> extends AbstractDBRowPropertyRenderer<R, DBLargeObject<T>, T> {

	public DBLargeObjectRenderer(R example, DBLargeObject<T> fieldOfExample) {
		super(new DBLargeObjectLabelGenerator<R, T>(example, fieldOfExample));
	}

}
