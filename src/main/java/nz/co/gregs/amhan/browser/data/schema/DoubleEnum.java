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
public enum DoubleEnum implements DBEnumValue<Double>{
	
	FIRST(1.3),
	SECOND(2.5),
	THIRD(3.7),
	FOURTH(4.9),
	FIFTH(5.2);
	private final Double code;
	
	private DoubleEnum(double code){
		this.code = code;
	}

	@Override
	public Double getCode() {
		return code;
	}
	
}
