/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.renderer;

import nz.co.gregs.amhan.browser.grid.labelgenerator.DBLargeTextLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLargeText;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBLargeTextRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBLargeText, byte[]> {

	public DBLargeTextRenderer(R example, DBLargeText fieldOfExample) {
		super(new DBLargeTextLabelGenerator<R>(example,fieldOfExample));
	}

}
