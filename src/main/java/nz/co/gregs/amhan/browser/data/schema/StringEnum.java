/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.data.schema;

import nz.co.gregs.dbvolution.datatypes.DBEnumValue;

/**
 *
 * @author gregorygraham
 */
public enum StringEnum implements DBEnumValue<String>{
	
	PREPARATION("FIRST"),
	MEETING("2"),
	PLANNING("3rd"),
	GATHER("QUAD"),
	IMPLEMENT("CINQUE");
	private final String code;
	
	private StringEnum(String code){
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
	
}
