/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.labelgenerator;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBBooleanArray;
import nz.co.gregs.separatedstring.SeparatedString;
import nz.co.gregs.separatedstring.SeparatedStringBuilder;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBBooleanArrayLabelGenerator<ROW extends DBRow> extends AbstractDBRowPropertyLabelGenerator<ROW, DBBooleanArray> {

	public DBBooleanArrayLabelGenerator(ROW example, DBBooleanArray qdt) {
		super(example, qdt);
	}

	@Override
	public String apply(ROW item) {
		DBBooleanArray qdt = getQDT(item);
		Boolean[] value = qdt.getValue();
		if (value==null || value.length==0){
			return "<NULL>";
		}else{
			SeparatedString builder = SeparatedStringBuilder.byCommaSpace().withPrefix("[").withSuffix("]");
			for (Boolean bool : value) {
				builder.add(bool.toString());
			}
			return builder.toString();
		}
	}

}
