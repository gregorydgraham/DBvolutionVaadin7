/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import java.util.UUID;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBUUID;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBUUIDField<ROW extends DBRow> extends QueryableDatatypeField<ROW, UUID, DBUUID> {

	private TextField textField;
	private Button button;

	public DBUUIDField(ROW row, DBUUID qdt) {
		super(null, row, qdt);
	}

	private static UUID changeToUUID(String e) {
		if (e != null) {
			try {
				return UUID.fromString(e);
			} catch (IllegalArgumentException ex) {
				;
			}
		}
		return null;
	}

	@Override
	protected void setPresentationValue(UUID newPresentationValue) {
		if (newPresentationValue != null) {
			textField.setValue(newPresentationValue.toString());
		} else {
			textField.clear();
		}
	}

	@Override
	protected void addInternalComponents(DBUUID qdt) {
		add(textField, button);
	}

	@Override
	protected void createInternalComponents() {
		textField = new TextField();
		button = new Button("<- UUID");
	}

	@Override
	protected void addInternalValueChangeListeners() {
		textField.addValueChangeListener(
				e -> updateQDT(changeToUUID(e.getValue())));
		button.addClickListener(t -> textField.setValue(UUID.randomUUID().toString()));
	}
}
