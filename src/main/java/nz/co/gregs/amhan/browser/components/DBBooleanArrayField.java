/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.shared.Registration;
import java.util.ArrayList;
import java.util.List;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBBooleanArray;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBBooleanArrayField<ROW extends DBRow> extends QueryableDatatypeField<ROW, Boolean[], DBBooleanArray> {

	Label label = new Label();
	List<Checkbox> checkBoxes = new ArrayList<Checkbox>(0);
	Div boxHolder = new Div();

	public static <ROW extends DBRow> DBBooleanArrayField<ROW> getField(ROW row, DBBooleanArray qdt) {
		return new DBBooleanArrayField<>(row, qdt);
	}

	public DBBooleanArrayField(ROW row, DBBooleanArray qdt) {
		super(new Boolean[]{}, row, qdt);
		initComponents(qdt);
	}

	protected final void initComponents(DBBooleanArray qdt) {
		createInternalComponents(qdt);
		setPresentationValue(qdt.getValue());
		setInternalLabel();
		addValueChangeListeners();
	}

	protected final void setInternalLabel() {
		label.setText(getLabel());
	}

	protected final void createInternalComponents(DBBooleanArray qdt) {
		Boolean[] array = qdt.booleanArrayValue();
		for (Boolean bool : array) {
			Checkbox box = new Checkbox(bool);
			checkBoxes.add(box);
			boxHolder.add(box);
		}
		add(new Div(label));
		add(boxHolder);
	}

	@Override
	protected final void setPresentationValue(Boolean[] value) {
		for (int i = 0; i < value.length; i++) {
			checkBoxes.get(i).setValue(value[i]);
		}
	}

	private void updateBooleanArray(AbstractField.ComponentValueChangeEvent<Checkbox, Boolean> event) {
		List<Boolean> bools = new ArrayList<>(0);
		for (Checkbox box : checkBoxes) {
			bools.add(box.getValue());
		}
		updateQDT(bools.toArray(new Boolean[]{}));
	}

	private void addValueChangeListeners() {
		for (Checkbox box : checkBoxes) {
			box.addValueChangeListener(event -> updateBooleanArray(event));
		}
	}

}
