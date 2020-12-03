/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import nz.co.gregs.amhan.components.grid.labelgenerator.DBStatisticsLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBStatistics;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;
import nz.co.gregs.dbvolution.expressions.EqualExpression;
import nz.co.gregs.dbvolution.results.EqualResult;
import nz.co.gregs.dbvolution.results.ExpressionHasStandardStringResult;

/**
 *
 * @author gregorygraham
 * @param <A>
 * @param <B>
 * @param <D>
 * @param <R>
 * @param <X>
 */
public class DBStatisticsRenderer<A extends DBRow, B, R extends EqualResult<B>, D extends QueryableDatatype<B>, X extends EqualExpression<B, R, D> & ExpressionHasStandardStringResult> extends AbstractDBRowPropertyRenderer<A, DBStatistics<B, R, D, X>, String> {

	public DBStatisticsRenderer(A example, DBStatistics<B, R, D, X> fieldOfExample) {
		super(new DBStatisticsLabelGenerator<A, B, R, D, X>(example, fieldOfExample));
	}

}
