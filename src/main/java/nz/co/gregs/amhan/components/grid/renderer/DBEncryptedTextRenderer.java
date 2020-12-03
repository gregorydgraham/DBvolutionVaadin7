/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBEncryptedTextLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBEncryptedText;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBEncryptedTextRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBEncryptedText, byte[]> {

	public DBEncryptedTextRenderer(R example, DBEncryptedText fieldOfExample) {
		super(new DBEncryptedTextLabelGenerator<R>(example, fieldOfExample));
	}

}
