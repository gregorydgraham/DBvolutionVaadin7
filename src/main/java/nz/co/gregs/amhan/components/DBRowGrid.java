/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import nz.co.gregs.dbvolution.utility.comparators.RowPropertyComparator;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.gregs.amhan.browser.grid.renderer.DBRowPropertyRenderer;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.databases.DBDatabase;
import nz.co.gregs.dbvolution.exceptions.AccidentalBlankQueryException;
import nz.co.gregs.dbvolution.exceptions.AccidentalCartesianJoinException;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 * @param <ROW> The DBRow to be show using this grid
 */
public class DBRowGrid<ROW extends DBRow> extends Grid<ROW> {

	private final DBDatabase database;
	private final ROW example;

	@SuppressWarnings("unchecked")
	public DBRowGrid(DBDatabase db, ROW example) {
		super((Class<ROW>) example.getClass(), false);
		this.database = db;
		this.example = example;
		setDefaultColumns(example);
		setItemsToDefaultSelection(example);
		setDefaultTheme();
		setDefaultSelectionModel();
	}

	public final void setDefaultSelectionModel() {
		this.asSingleSelect().addValueChangeListener((event) -> {
			DBRow value = event.getValue();
			if (value != null) {
				Notification.show("Selected " + value.getClass().getCanonicalName());
			}
		});
	}

	public final void setDefaultTheme() {
		addThemeVariants(GridVariant.LUMO_NO_BORDER);
		setHeightFull();
	}

	public final void setItemsToDefaultSelection(ROW example) {
		setItemsToAllRows(example);
	}

	public final void setItemsToAllRows(ROW example) {
		final List<ROW> allRows;
		try {
			allRows = database.getDBTable(example).setBlankQueryAllowed(true).getAllRows();
			setItems(allRows);
		} catch (SQLException | AccidentalCartesianJoinException | AccidentalBlankQueryException ex) {
			Logger.getLogger(DBRowGrid.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public final <A extends Object> void setDefaultColumns(ROW example) {
		setColumns();
		List<PropertyWrapper<?, ?, ?>> wrapper = example.getColumnPropertyWrappers();
		wrapper.forEach(
				prop -> {
					final RowPropertyComparator<ROW> rowPropertyComparator = new RowPropertyComparator<ROW>(example, prop.getQueryableDatatype());
					addColumn(
							DBRowPropertyRenderer.getRenderer(example, prop))
							.setHeader(prop.javaName())
							.setComparator(rowPropertyComparator)
							.setSortable(true)
							.setResizable(true);
				}
		);
	}

	public void refreshItem(ROW updatedRow) {
		setItemsToAllRows(example);
		this.select(updatedRow);
	}

}
