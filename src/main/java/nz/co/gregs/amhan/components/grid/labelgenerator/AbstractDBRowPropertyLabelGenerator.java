/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.labelgenerator;

import com.vaadin.flow.component.ItemLabelGenerator;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapperDefinition;

/**
 *
 * @author gregorygraham
 * @param <R>
 * @param <Q>
 */
public abstract class AbstractDBRowPropertyLabelGenerator<R extends DBRow, Q extends QueryableDatatype> implements ItemLabelGenerator<R> {

	final PropertyWrapperDefinition pwDefn;

	protected AbstractDBRowPropertyLabelGenerator(R example, Q fieldOfThisExample) {
		this(example.getPropertyWrapperOf(fieldOfThisExample).getPropertyWrapperDefinition());
	}

	private AbstractDBRowPropertyLabelGenerator(PropertyWrapperDefinition prop) {
		pwDefn = prop;
	}

	protected PropertyWrapperDefinition getPropertyWrapperDefinition() {
		return pwDefn;
	}

	@SuppressWarnings("unchecked")
	protected Q getQDT(R item) {
		return (Q) getPropertyWrapperDefinition().getQueryableDatatype(item);
	}
}
