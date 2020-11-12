/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.valueprovider;

import com.vaadin.flow.function.ValueProvider;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.*;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapperDefinition;

/**
 *
 * @author gregorygraham
 * @param <ROW> the source row of from the database containing the QDT
 * @param <TYPE> the type used by the QDT's getValue and setValue methods
 * @param <QDT> the QDT containing the value from the database
 */
public class QueryableDatatypeValueProvider<ROW extends DBRow, TYPE, QDT extends QueryableDatatype<TYPE>> implements ValueProvider<ROW, TYPE> {

	@Override
	@SuppressWarnings("unchecked")
	public TYPE apply(ROW source) {
		final PropertyWrapper wrapper = getInternalPropertyWrapper();
		final PropertyWrapperDefinition pwDefn = wrapper.getPropertyWrapperDefinition();
		final QueryableDatatype<TYPE> queryableDatatype = pwDefn.getQueryableDatatype(source);
		final TYPE value = queryableDatatype.getValue();
		return value;
	}

	/**
	 * Synonym for {@link #getValueProvider(nz.co.gregs.dbvolution.DBRow, nz.co.gregs.dbvolution.datatypes.QueryableDatatype)
	 * }
	 *
	 * @param <ROW>
	 * @param <TYPE>
	 * @param <QDT>
	 * @param example
	 * @param qdt
	 * @return
	 */
	public static <ROW extends DBRow, TYPE, QDT extends QueryableDatatype<TYPE>> QueryableDatatypeValueProvider<ROW, TYPE, QDT> get(ROW example, QDT qdt) {
		return getValueProvider(example, qdt);
	}

	/**
	 * Synonym for {@link #getValueProvider(nz.co.gregs.dbvolution.DBRow, nz.co.gregs.dbvolution.datatypes.QueryableDatatype)
	 * }
	 *
	 * @param <ROW>
	 * @param <TYPE>
	 * @param <QDT>
	 * @param example
	 * @param qdt
	 * @return
	 */
	public static <ROW extends DBRow, TYPE, QDT extends QueryableDatatype<TYPE>> QueryableDatatypeValueProvider<ROW, TYPE, QDT> getValueProvider(ROW example, QDT qdt) {
		return new QueryableDatatypeValueProvider<ROW, TYPE, QDT>(example, qdt);
	}

	private QueryableDatatypeValueProvider(ROW example, QDT qdt) {
		this(example.getPropertyWrapperOf(qdt));
	}

	private QueryableDatatypeValueProvider(PropertyWrapper<ROW, TYPE, QDT> wrapper) {
		setInternalPropertyWrapper(wrapper);
	}

	private PropertyWrapper internalPropertyWrapper;

	/**
	 * @return the internalPropertyWrapper
	 */
	private PropertyWrapper getInternalPropertyWrapper() {
		return internalPropertyWrapper;
	}

	private void setInternalPropertyWrapper(PropertyWrapper internalPropertyWrapper) {
		this.internalPropertyWrapper = internalPropertyWrapper;
	}

}
