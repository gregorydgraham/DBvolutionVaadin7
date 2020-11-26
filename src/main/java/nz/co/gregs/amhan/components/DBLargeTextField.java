/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.textfield.TextArea;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLargeText;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBLargeTextField<ROW extends DBRow> extends QueryableDatatypeField<ROW, byte[], DBLargeText> {

	TextArea largeTextField;

	public DBLargeTextField(ROW row, DBLargeText qdt) {
		super(new byte[]{}, row, qdt);
	}

	@Override
	protected final void setPresentationValue(byte[] value) {
		if (value != null) {
			largeTextField.setValue(DBLargeText.transformToStandardCharset(value));
		} else {
			largeTextField.clear();
		}
	}

	@Override
	protected void addInternalComponents(DBLargeText qdt) {
		add(largeTextField);
	}

	@Override
	protected void createInternalComponents() {
		largeTextField = new TextArea();
	}

	@Override
	protected void addInternalValueChangeListeners() {
		largeTextField.addValueChangeListener(e -> updateSourceText(e));
	}

	private void updateSourceText(ComponentValueChangeEvent<TextArea, String> event) {
		updateQDT(
				event.getValue(),
				val -> DBLargeText.transformToStandardCharset(val)
		);
	}

}
