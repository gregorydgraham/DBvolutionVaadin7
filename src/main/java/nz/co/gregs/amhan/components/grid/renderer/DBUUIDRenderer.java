/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import java.util.UUID;
import nz.co.gregs.amhan.components.grid.labelgenerator.DBUUIDLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBUUID;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBUUIDRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBUUID, UUID> {

	public DBUUIDRenderer(R row, DBUUID fieldOfThisRow) {
		super(new DBUUIDLabelGenerator<R>(row, fieldOfThisRow));
	}

}
