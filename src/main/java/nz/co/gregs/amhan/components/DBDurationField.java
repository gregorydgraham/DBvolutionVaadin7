/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.textfield.IntegerField;
import java.time.*;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBDuration;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBDurationField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Duration, DBDuration> {

	Checkbox isNegative;
	IntegerField secondsField;
	IntegerField nanosField;

	public DBDurationField(ROW row, DBDuration qdt) {
		super(null, row, qdt);
	}

	@Override
	protected final void setPresentationValue(Duration newPresentationValue) {
		if (newPresentationValue != null&& !newPresentationValue.isZero()) {
			secondsField.setValue(Long.valueOf(newPresentationValue.getSeconds()).intValue());
			nanosField.setValue(newPresentationValue.getNano());
			isNegative.setValue(newPresentationValue.isNegative());
		} else {
			secondsField.clear();
			nanosField.clear();
			isNegative.clear();
		}
	}

	@Override
	protected void addInternalComponents(DBDuration qdt) {
		add(isNegative, secondsField,nanosField);
	}

	@Override
	protected void createInternalComponents() {
		isNegative = new Checkbox("Is Negative");
		secondsField = new IntegerField("Seconds");
		nanosField = new IntegerField("Nanos");
	}

	@Override
	protected void addInternalValueChangeListeners() {
		isNegative.addValueChangeListener(e -> update(e));
		secondsField.addValueChangeListener(e -> update(e));
		nanosField.addValueChangeListener(e -> update(e));
	}

	private void update(AbstractField.ComponentValueChangeEvent<?, ?> e) {
		final int seconds = (isNegative.getValue()?-1:1)*secondsField.getValue();
		final Integer nanos = nanosField.getValue();
		Duration duration = Duration.ofSeconds(seconds, nanos);
		updateQDT(duration);
	}
}
