/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.grid.renderer;

import com.vaadin.flow.component.Component;
import nz.co.gregs.amhan.components.grid.labelgenerator.AbstractDBRowPropertyLabelGenerator;
import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public class AbstractDBRowPropertyRenderer<R extends DBRow, Q extends QueryableDatatype<T>, T> extends ComponentRenderer<Component, R> {

	private AbstractDBRowPropertyLabelGenerator<R, Q> generator;

	/**
	 * Creates a new renderer instance using the default
	 * {@link ItemLabelGenerator}: <code>String::valueOf</code>.
	 */
	public AbstractDBRowPropertyRenderer() {
		super();
	}

	/**
	 * Creates a new renderer instance using the provided
	 * {@code itemLabelGenerator}.
	 *
	 * @param itemLabelGenerator the item label generator
	 */
	public AbstractDBRowPropertyRenderer(AbstractDBRowPropertyLabelGenerator<R, Q> itemLabelGenerator) {
		this.generator = itemLabelGenerator;
	}

	@Override
	public Component createComponent(R row) {
		String text = generator.apply(row);
		if (text == null) {
			throw new IllegalStateException(String.format(
					"Got 'null' as a label value for the item '%s'. "
					+ "'%s' instance may not return 'null' values",
					row, ItemLabelGenerator.class.getSimpleName()));
		}
		return new Span(text);//new ItemRendererComponent(createElement(text));
	}
}
