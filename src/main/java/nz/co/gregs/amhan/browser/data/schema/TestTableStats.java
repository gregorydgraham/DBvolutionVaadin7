/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.data.schema;

import nz.co.gregs.dbvolution.annotations.DBColumn;
import nz.co.gregs.dbvolution.datatypes.DBNumberStatistics;

/**
 *
 * @author gregorygraham
 */
public class TestTableStats extends TestTable {

	@DBColumn
	DBNumberStatistics ratingsStats = new DBNumberStatistics(this.column(rating));

	{
		this.setReturnFields(ratingsStats);
	}

	public TestTableStats() {
	}

}
