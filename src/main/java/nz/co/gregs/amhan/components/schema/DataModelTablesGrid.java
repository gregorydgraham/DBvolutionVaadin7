/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components.schema;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import java.util.List;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.reflection.DataModel;

/**
 *
 * @author gregorygraham
 */
public class DataModelTablesGrid extends Grid<DBRow> {

	public DataModelTablesGrid() {
		addColumn((source) -> {
			return source.getClass().getSimpleName();
		}).setHeader("Class");
		addColumn((source) -> {
			return source.getTableName();
		}).setHeader("TableName");
		addColumn((source) -> {
			return source.getClass().getCanonicalName();
		}).setHeader("Canonical Class");

		setItems(getDBRowInstances());

		addThemeVariants(GridVariant.LUMO_NO_BORDER);
		setWidthFull();
		setHeightFull();
	}

	private List<DBRow> getDBRowInstances() {
		final List<DBRow> dbRowInstances = DataModel.getDBRowInstances();
		dbRowInstances.sort((o1, o2) -> {
			return o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
		});
		return dbRowInstances;
	}
}
