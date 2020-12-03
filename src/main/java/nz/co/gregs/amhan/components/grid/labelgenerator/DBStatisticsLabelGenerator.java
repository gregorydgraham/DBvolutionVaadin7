/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.labelgenerator;

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
 * @param <R>
 * @param <D>
 * @param <X>
 */
public class DBStatisticsLabelGenerator<A extends DBRow,B, R extends EqualResult<B>, D extends QueryableDatatype<B>, X extends EqualExpression<B, R, D> & ExpressionHasStandardStringResult> extends AbstractDBRowPropertyLabelGenerator<A, DBStatistics<B,R,D,X>> {

	public DBStatisticsLabelGenerator(A example, DBStatistics<B,R,D,X> qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(A item) {
		return getQDT(item).stringValue();
	}

}
