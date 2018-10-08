/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.dbvolutionvaadin7.component;

import com.vaadin.flow.component.textfield.TextArea;
import nz.co.gregs.dbvolution.datatypes.DBString;

public class DBStringNullableComponent extends AbstractNullableDBComponent<String, String, DBString, TextArea> {

	public DBStringNullableComponent(String defaultValue) {
		super(defaultValue);
	}

	@Override
	public TextArea getComponent() {
		final TextArea text = new TextArea();
		return text;
	}

	@Override
	public TextArea getComponentForQDT(DBString qdt) {
		final TextArea text = getComponent();
		text.addValueChangeListener((event) -> {
			qdt.setValue(event.getValue());
		});
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
