/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.textfield.IntegerField;
import org.joda.time.Period;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBDateRepeat;
import org.joda.time.DateTime;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBDateRepeatField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Period, DBDateRepeat> {

	Checkbox isNegative;
	IntegerField yearsField;
	IntegerField monthsField;
	IntegerField daysField;
	IntegerField hoursField;
	IntegerField minutesField;
	IntegerField secondsField;
	IntegerField millisField;

	public DBDateRepeatField(ROW row, DBDateRepeat qdt) {
		super(null, row, qdt);
	}

	@Override
	protected final void setPresentationValue(Period newPresentationValue) {
		if (newPresentationValue != null) {
			if (isNonZero(newPresentationValue)) {
				System.out.println("setPresentationValue() : "+newPresentationValue);
				yearsField.setValue(newPresentationValue.getYears());

				monthsField.setValue(newPresentationValue.getMonths());
				daysField.setValue(newPresentationValue.getDays());
				hoursField.setValue(newPresentationValue.getHours());
				minutesField.setValue(newPresentationValue.getMinutes());
				secondsField.setValue(newPresentationValue.getSeconds());
				millisField.setValue(newPresentationValue.getMillis());

				isNegative.setValue(isNegativePeriod(newPresentationValue));
			} else {
				clear();
			}
		} else {
			clear();
		}
	}

	public static boolean isNegativePeriod(Period newPresentationValue) {
		DateTime now = org.joda.time.DateTime.now();
		return now.plus(newPresentationValue).isBefore(now);
	}

	public static boolean isNonZero(Period newPresentationValue) {
		return newPresentationValue.getYears() != 0
				|| newPresentationValue.getMonths() != 0
				|| newPresentationValue.getDays() != 0
				|| newPresentationValue.getHours() != 0
				|| newPresentationValue.getMinutes() != 0
				|| newPresentationValue.getSeconds() != 0
				|| newPresentationValue.getMillis() != 0;
	}

	@Override
	public void clear() {
		super.clear();
		yearsField.clear();
		monthsField.clear();
		daysField.clear();
		hoursField.clear();
		minutesField.clear();
		secondsField.clear();
		millisField.clear();
		isNegative.clear();
	}

	@Override
	protected void addInternalComponents(DBDateRepeat qdt) {
		add(isNegative, yearsField, monthsField, daysField,hoursField,minutesField,secondsField,millisField);
	}

	@Override
	protected void createInternalComponents() {
		isNegative = new Checkbox("Is Negative");
		yearsField = new IntegerField("Years");
		monthsField = new IntegerField("Months");
		daysField = new IntegerField("Days");
		hoursField = new IntegerField("Hours");
		minutesField = new IntegerField("Minutes");
		secondsField = new IntegerField("Seconds");
		millisField = new IntegerField("Millis");
	}

	@Override
	protected void addInternalValueChangeListeners() {
		isNegative.addValueChangeListener(e -> update(e));
		yearsField.addValueChangeListener(e -> update(e));
		monthsField.addValueChangeListener(e -> update(e));
		daysField.addValueChangeListener(e -> update(e));
		hoursField.addValueChangeListener(e -> update(e));
		minutesField.addValueChangeListener(e -> update(e));
		secondsField.addValueChangeListener(e -> update(e));
		millisField.addValueChangeListener(e -> update(e));
	}

	private void update(AbstractField.ComponentValueChangeEvent<?, ?> e) {

		final Integer years = yearsField.getValue();
		final Integer months = monthsField.getValue();
		final Integer days = daysField.getValue();
		final Integer hours = hoursField.getValue();
		final Integer minutes = minutesField.getValue();
		final Integer seconds = secondsField.getValue();
		final Integer millis = millisField.getValue();

		Period period = new Period()
				.withYears(years)
				.withMonths(months)
				.withDays(days)
				.withHours(hours)
				.withMinutes(minutes)
				.withSeconds(seconds)
				.withMillis(millis);

		if (isNegative.getValue()) {
			period.negated();
		}

		updateQDT(period);
	}
}
