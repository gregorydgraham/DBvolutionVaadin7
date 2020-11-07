/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.grid.renderer;

import com.vaadin.flow.data.renderer.LocalDateRenderer;
import nz.co.gregs.amhan.browser.valueprovider.QueryableDatatypeValueProvider;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLocalDate;

/**
 *
 * @author gregorygraham
 * @param <R>
 */
public class DBLocalDateRenderer<R extends DBRow> extends LocalDateRenderer<R> {

	public DBLocalDateRenderer(R example, DBLocalDate propertyOfThisExample) {
		super(QueryableDatatypeValueProvider.get(example, propertyOfThisExample));
	}
}
