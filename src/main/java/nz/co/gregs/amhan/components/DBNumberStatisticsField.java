/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.textfield.NumberField;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBNumberStatistics;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBNumberStatisticsField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Number, DBNumberStatistics> {

	private NumberField minNumber;
	private NumberField maxNumber;
	private NumberField averageNumber;
	private NumberField stdDev;
	private NumberField countOfRows;
	private NumberField sum;

	public DBNumberStatisticsField(ROW row, DBNumberStatistics qdt) {
		super(null, row, qdt);
		System.out.println("DBNumberStatisticsField INIT: "+qdt.toString());
	}

	@Override
	protected final void setPresentationValue(Number newPresentationValue) {
		DBNumberStatistics qdt = getQueryableDatatype();
		if (qdt != null) {
			System.out.println("STATS: " + qdt);
			countOfRows.setValue(qdt.count() == null ? null : qdt.count().doubleValue());
			sum.setValue(qdt.sum() == null ? null : qdt.sum().doubleValue());
			minNumber.setValue(qdt.min() == null ? null : qdt.min().doubleValue());
			maxNumber.setValue(qdt.max() == null ? null : qdt.max().doubleValue());
			averageNumber.setValue(qdt.average() == null ? null : qdt.average().doubleValue());
			stdDev.setValue(qdt.standardDeviation() == null ? null : qdt.standardDeviation().doubleValue());
		} else {
			clear();
		}
	}

	@Override
	public void clear() {
		super.clear();
		minNumber.clear();
		maxNumber.clear();
		averageNumber.clear();
		stdDev.clear();
		countOfRows.clear();
		sum.clear();
	}

	@Override
	protected void addInternalComponents(DBNumberStatistics qdt) {
		add(
				countOfRows,
				sum,
				minNumber,
				maxNumber,
				averageNumber,
				stdDev
		);
	}

	@Override
	protected void createInternalComponents() {
		countOfRows = new NumberField("Count");
		sum = new NumberField("Sum");
		minNumber = new NumberField("Minimum");
		maxNumber = new NumberField("Maximum");
		averageNumber = new NumberField("Average");
		stdDev = new NumberField("Std Deviation");
	}

	@Override
	protected void addInternalValueChangeListeners() {
		// Statistics are generated and can't be changed manually
	}
}
