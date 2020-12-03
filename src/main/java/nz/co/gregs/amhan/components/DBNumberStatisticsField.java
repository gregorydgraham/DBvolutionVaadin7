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
	private NumberField medianNumber;
	private NumberField averageNumber;
	private NumberField stdDev;
	private NumberField firstQuartileNumber;
	private NumberField thirdQuartileNumber;
	private NumberField countOfRows;
	private NumberField sum;

	public DBNumberStatisticsField(ROW row, DBNumberStatistics qdt) {
		super(null, row, qdt);
	}

	@Override
	protected final void setPresentationValue(Number newPresentationValue) {
		DBNumberStatistics qdt = getQueryableDatatype();
		if (qdt != null) {
			System.out.println("STATS: " + qdt);
			countOfRows.setValue(qdt.count().doubleValue());
			sum.setValue(qdt.sum().doubleValue());
			minNumber.setValue(qdt.min().doubleValue());
			maxNumber.setValue(qdt.max().doubleValue());
			averageNumber.setValue(qdt.average().doubleValue());
			if (qdt.standardDeviation() != null) {
				stdDev.setValue(qdt.standardDeviation().doubleValue());
			}
//			medianNumber.setValue(qdt.median().doubleValue());
//			firstQuartileNumber.setValue(qdt.firstQuartile().doubleValue());
//			thirdQuartileNumber.setValue(qdt.thirdQuartile().doubleValue());
		} else {
			clear();
		}
	}

	@Override
	public void clear() {
		super.clear();
		minNumber.clear();
		maxNumber.clear();
		medianNumber.clear();
		averageNumber.clear();
		stdDev.clear();
		firstQuartileNumber.clear();
		thirdQuartileNumber.clear();
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
		//				,medianNumber,
		//				firstQuartileNumber,
		//				thirdQuartileNumber
		);
	}

	@Override
	protected void createInternalComponents() {
		countOfRows = new NumberField("Count");
		sum = new NumberField("Sum");
		minNumber = new NumberField("Minimum");
		maxNumber = new NumberField("Maximum");
		medianNumber = new NumberField("Median");
		averageNumber = new NumberField("Average");
		stdDev = new NumberField("Std Deviation");
		firstQuartileNumber = new NumberField("1st Quartile");
		thirdQuartileNumber = new NumberField("3rd Quartile");
	}

	@Override
	protected void addInternalValueChangeListeners() {
		// Statistics are generated and can't be changed manually
	}
}
