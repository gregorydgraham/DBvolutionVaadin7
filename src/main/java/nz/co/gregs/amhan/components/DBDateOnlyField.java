/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.datepicker.DatePicker;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBDateOnly;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBDateOnlyField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Date, DBDateOnly> {

	DatePicker picker;

	public DBDateOnlyField(ROW row, DBDateOnly qdt) {
		super(new Date(), row, qdt);
	}

	@Override
	protected final void setPresentationValue(Date value) {
		if (value != null) {
			picker.setValue(
					value.toInstant()
							.atZone(ZoneId.systemDefault())
							.toLocalDate()
			);
		} else {
			picker.clear();
		}
	}

	@Override
	protected void addInternalComponents(DBDateOnly qdt) {
		add(picker);
	}

	@Override
	protected void createInternalComponents() {
		picker = new DatePicker();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		picker.addValueChangeListener(e -> updateDateOnly(e));
	}

	private void updateDateOnly(AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate> e) {
		if (e.getValue() == null) {
			updateQDT((Date)null);
		} else {
			updateQDT(
					Date.from(
							e.getValue()
									.atStartOfDay()
									.atZone(ZoneId.systemDefault())
									.toInstant()
					)
			);
		}
	}

}
