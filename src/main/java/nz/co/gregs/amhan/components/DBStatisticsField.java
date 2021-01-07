/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBStatistics;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;
import nz.co.gregs.dbvolution.expressions.EqualExpression;
import nz.co.gregs.dbvolution.results.EqualResult;
import nz.co.gregs.dbvolution.results.ExpressionHasStandardStringResult;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 * @param <B>
 * @param <R>
 * @param <D>
 * @param <X>
 */
public class DBStatisticsField<ROW extends DBRow,B, R extends EqualResult<B>, D extends QueryableDatatype<B>, X extends EqualExpression<B, R, D> & ExpressionHasStandardStringResult> extends QueryableDatatypeField<ROW, String, DBStatistics<B,R,D,X>> {

	private TextField modeSimple;
	private TextField modeStrict;
	private NumberField countOfRows;

	public DBStatisticsField(ROW row, DBStatistics<B,R,D,X> qdt) {
		super(null, row, qdt);
	}

	@Override
	protected final void setPresentationValue(String newPresentationValue) {
		DBStatistics qdt = getQueryableDatatype();
		if (qdt != null) {
			System.out.println("STATS: " + qdt);
			countOfRows.setValue(qdt.count().doubleValue());
			modeSimple.setValue(qdt.modeSimple().toString());
			modeStrict.setValue(qdt.modeStrict().toString());
		} else {
			clear();
		}
	}

	@Override
	public void clear() {
		super.clear();
		modeSimple.clear();
		modeStrict.clear();
		countOfRows.clear();
	}

	@Override
	protected void addInternalComponents(DBStatistics qdt) {
		add(
				countOfRows,
				modeSimple,
				modeStrict
		);
	}

	@Override
	protected void createInternalComponents() {
		countOfRows = new NumberField("Count");
		modeSimple = new TextField("Minimum");
		modeStrict = new TextField("Maximum");
	}

	@Override
	protected void addInternalValueChangeListeners() {
		// Statistics are generated and can't be changed manually
	}
}
