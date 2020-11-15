/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import java.time.LocalDateTime;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLocalDateTime;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBLocalDateTimeField<ROW extends DBRow> extends QueryableDatatypeField<ROW, LocalDateTime, DBLocalDateTime> {

	DateTimePicker picker;

	public DBLocalDateTimeField(ROW row, DBLocalDateTime qdt) {
		super(LocalDateTime.now(), row, qdt);
	}

	@Override
	protected final void setPresentationValue(LocalDateTime newPresentationValue) {
		picker.setValue(newPresentationValue);
	}

	@Override
	protected void addInternalComponents(DBLocalDateTime qdt) {
		add(picker);
	}

	@Override
	protected void createInternalComponents() {
		picker = new DateTimePicker();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		picker.addValueChangeListener(e -> updateQDT(e));
	}
}
