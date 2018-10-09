/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.dbvolutionvaadin7.component;

import com.vaadin.flow.component.textfield.TextField;
import nz.co.gregs.dbvolution.datatypes.DBInteger;

public class DBIntegerNullableComponent extends AbstractNullableDBComponent<Long, String, DBInteger, TextField> {

	public DBIntegerNullableComponent(DBInteger qdt, String label, String defaultValue) {
		super(qdt, label, defaultValue);
	}

	@Override
	public IntegerField getComponent() {
		final IntegerField text = new IntegerField();
		return text;
	}

//	@Override
//	public IntegerField getComponentForQDT(DBInteger qdt) {
//		final IntegerField numberField = getComponent();
//		numberField.addValueChangeListener((event) -> {
//			qdt.setValue(event.getValue());
//		});
//		return numberField;
//	}

	@Override
	protected String convertDBValueToComponentValue(Long value) {
		return ""+value;
	}

	@Override
	protected Long convertComponentValueToDBValue(String value) {
		return Long.valueOf(value.replaceAll(".*?([-]?[0-9]+).*", "\\1"));
	}
	
	

	public static class IntegerField extends TextField {

		public IntegerField() {
			setPreventInvalidInput(true);
			setPattern("[-]?[0-9]+");
		}
	}
}
