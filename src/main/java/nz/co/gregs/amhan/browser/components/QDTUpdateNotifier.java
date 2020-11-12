/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.shared.Registration;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 * @param <T>
 * @param <QDT>
 */
public interface QDTUpdateNotifier<T,QDT extends QueryableDatatype<T>> {

	@SuppressWarnings(value = "unchecked")
	default Registration addQDTUpdateListener(ComponentEventListener<Event<T,QDT>> listener) {
		if (this instanceof Component) {
			return ComponentUtil.addListener(
					(Component) this,
					Event.class,
					(ComponentEventListener) listener
			);
		} else {
			throw new IllegalStateException(String.format(
					"The class '%s' doesn't extend '%s'. "
					+ "Make your implementation for the method '%s'.",
					getClass().getName(), Component.class.getSimpleName(),
					"addDBRowUpdatedListener"));
		}
	}

	public static class Event<T,QDT extends QueryableDatatype<T>> extends ComponentEvent<QueryableDatatypeField<?,T,QDT>> {

		private final QDT updatedQDT;

		public Event(QueryableDatatypeField<?,T,QDT> source) {
			super(source, true);
			updatedQDT = source.getQueryableDatatype();
		}

		public QDT getUpdatedQDT() {
			return updatedQDT;
		}		
		
	}

}
