/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.dbvolutionvaadin7.component;

import com.vaadin.flow.component.textfield.TextField;
import nz.co.gregs.dbvolution.datatypes.DBString;

public class DBStringFieldNullableComponent extends AbstractNullableDBComponent<String, String, DBString, TextField> {

	public DBStringFieldNullableComponent(DBString qdt, String label, String defaultValue) {
		super(qdt, label, defaultValue);
	}

	@Override
	public TextField getComponent() {
		final TextField text = new TextField();
		return text;
	}

	@Override
	protected String convertDBValueToComponentValue(String value) {
		return value;
	}

	@Override
	protected String convertComponentValueToDBValue(String value) {
		return value;
	}

}
