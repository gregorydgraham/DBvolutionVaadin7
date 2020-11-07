/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.setter;

import com.vaadin.flow.data.binder.Setter;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapperDefinition;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 * @param <TYPE>
 * @param <QDT>
 */
public class QueryableDatatypeSetter<ROW extends DBRow, TYPE, QDT extends QueryableDatatype<TYPE>> implements Setter<ROW, TYPE> {

	private final PropertyWrapperDefinition wrapper;

	protected QueryableDatatypeSetter(DBRow example, QDT qdt) {
		this(example.getPropertyWrapperOf(qdt));
	}

	protected QueryableDatatypeSetter(PropertyWrapper wrapper) {
		this(wrapper.getPropertyWrapperDefinition());
	}

	protected QueryableDatatypeSetter(PropertyWrapperDefinition wrapper) {
		this.wrapper = wrapper;
	}

	public PropertyWrapperDefinition getWrapper() {
		return wrapper;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void accept(ROW bean, TYPE fieldvalue) {
		getWrapper().getQueryableDatatype(bean).setValue(fieldvalue);
	}

	@SuppressWarnings("unchecked")
	public static <A extends DBRow, T, Q extends QueryableDatatype<T>> QueryableDatatypeSetter<A, T, Q> getSetter(A example, Q qdt) {
		return new QueryableDatatypeSetter<A, T, Q>(example, qdt);
	}

	@SuppressWarnings("unchecked")
	public static <A extends DBRow, T, Q extends QueryableDatatype<T>> QueryableDatatypeSetter<A, T, Q> getSetter(PropertyWrapper<?,?> propertyWrapper) {
		return new QueryableDatatypeSetter<A, T, Q>(propertyWrapper);
	}

}
