/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBNumberStatisticsLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBNumberStatistics;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBNumberStatisticsRenderer<R extends DBRow> extends AbstractDBRowPropertyRenderer<R, DBNumberStatistics, Number> {
	
	public DBNumberStatisticsRenderer(R example, DBNumberStatistics fieldOfExample) {
		super(new DBNumberStatisticsLabelGenerator<R>(example,fieldOfExample));
	}
	
}
