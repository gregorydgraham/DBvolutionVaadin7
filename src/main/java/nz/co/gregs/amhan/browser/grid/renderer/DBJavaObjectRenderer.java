/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.renderer;

import nz.co.gregs.amhan.browser.grid.labelgenerator.DBJavaObjectLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBJavaObject;

/**
 *
 * @author gregorygraham
 * @param <R>
 * @param <T>
 */
public class DBJavaObjectRenderer<R extends DBRow, T> extends AbstractDBRowPropertyRenderer<R, DBJavaObject<T>, T> {

	public DBJavaObjectRenderer(R example, DBJavaObject<T> fieldOfExample) {
		super(new DBJavaObjectLabelGenerator<R, T>(example, fieldOfExample));
	}

}
