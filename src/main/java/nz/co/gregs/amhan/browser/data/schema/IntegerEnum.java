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
public enum IntegerEnum implements DBEnumValue<Long>{
	
	FIRST(1),
	SECOND(2),
	THIRD(3),
	FOURTH(4),
	FIFTH(5);
	private final Long code;
	
	private IntegerEnum(int code){
		this.code = Long.valueOf(code);
	}

	@Override
	public Long getCode() {
		return code;
	}
	
}
