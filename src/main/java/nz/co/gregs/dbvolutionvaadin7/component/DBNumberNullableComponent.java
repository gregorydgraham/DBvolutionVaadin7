/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.dbvolutionvaadin7.component;

import com.vaadin.flow.component.textfield.TextField;
import nz.co.gregs.dbvolution.datatypes.DBNumber;

public class DBNumberNullableComponent extends AbstractNullableDBComponent<Number, String, DBNumber, TextField> {

	public DBNumberNullableComponent(DBNumber qdt, String label, String defaultValue) {
		super(qdt, label, defaultValue);
	}

	@Override
	public NumberField getComponent() {
		final NumberField text = new NumberField();
		return text;
	}

	@Override
	protected String convertDBValueToComponentValue(Number value) {
		return ""+value;
	}

	@Override
	protected Number convertComponentValueToDBValue(String value) {
		return Double.valueOf(value.replaceAll(".*?([-]?[0-9]+(\\.[0-9]+)?).*", "\\1"));
	}
	
	

	public static class NumberField extends TextField {

		public NumberField() {
			setPreventInvalidInput(true);
			setPattern("[-]?[0-9][0-9.,]*");
		}
	}
}
