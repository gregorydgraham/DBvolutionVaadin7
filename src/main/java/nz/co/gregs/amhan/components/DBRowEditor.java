/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.databases.DBDatabase;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;
import nz.co.gregs.dbvolution.internal.properties.PropertyWrapper;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBRowEditor<ROW extends DBRow> extends Composite<Div> implements DBRowUpdateNotifier<ROW>, EditingCancelledNotifier<ROW> {

	private Logger LOGGER = Logger.getLogger(DBRowEditor.class.getName());
	
	private final DBDatabase database;
	private final ROW row;
	private final Button cancel = new Button("Cancel");
	private final Button save = new Button("Save");
	private final List<QueryableDatatypeField<ROW, ?, ?>> qdtFields = new ArrayList<>();

	public DBRowEditor(DBDatabase database, ROW forThisRow) {
		this.database = database;
		row = DBRow.copyDBRow(forThisRow);

		createEditorLayout(row);

		addButtonClickListeners();
		
		addEditorListeners();

		setUnchanged();
	}

	private void createEditorLayout(ROW row) {
		setId("editor-layout");

		Div editorDiv = new Div();
		editorDiv.setId("editor");

		FormLayout formLayout = new FormLayout();
		addFields(formLayout, row);
		
		getContent().add(editorDiv);
		getContent().add(formLayout);
		getContent().add(createButtonLayout());
	}

	private HorizontalLayout createButtonLayout() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setId("button-layout");
		buttonLayout.setWidthFull();
		buttonLayout.setSpacing(true);
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		cancel.addClickListener(event -> cancelEditing());
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		buttonLayout.add(save, cancel);
		return buttonLayout;
	}

	private void addFields(FormLayout formLayout, ROW row) {
		var qdts = row.getColumnQueryableDatatypes();
		qdts.forEach(qdt -> addQueryableDatatypeField(qdt, row, formLayout));
	}

	private <T> void addQueryableDatatypeField(QueryableDatatype<T> qdt, ROW row, FormLayout formLayout) {
		QDTComponents<ROW, T> qdtComponent = QDTComponents.getFor(row, qdt);
		final QueryableDatatypeField<ROW, T, ?> editor = qdtComponent.getEditor();
		editor.getElement().getClassList().add("full-width");
		qdtFields.add(editor);
		formLayout.add(editor);
	}

	private void saveTheRow() {
		database.setPrintSQLBeforeExecuting(true);
		try {
			Notification.show("Saving...");
			final ROW row1 = getRow();
			database.update(row1);
			Notification.show("Saved.");
			reloadFields();
			tellObserversOfSaveEvent();
		} catch (SQLException ex) {
			Notification.show("SAVE FAILED: " + ex.getLocalizedMessage());
			ex.printStackTrace();
			LOGGER.log(Level.SEVERE, null, ex);
		}
		database.setPrintSQLBeforeExecuting(false);
	}

	protected final void addButtonClickListeners() {
		addButtonClickListeners(e -> saveTheRow());
	}

	protected void addButtonClickListeners(ComponentEventListener<ClickEvent<Button>> saveListener) {
		addButtonClickListeners(saveListener, e -> {
			Notification.show("Cancelled");
		});
	}

	protected void addButtonClickListeners(ComponentEventListener<ClickEvent<Button>> saveListener, ComponentEventListener<ClickEvent<Button>> cancelListener) {
		save.addClickListener(saveListener);
		cancel.addClickListener(cancelListener);
	}

	public ROW getRow() {
		return row;
	}

	public DBDatabase getDatabase() {
		return database;
	}

	private void tellObserversOfSaveEvent() {
		fireEvent(new DBRowUpdatedEvent<>(this));
	}

	private void reloadFields() {
		qdtFields.stream().forEach(f -> f.reloadValue());
	}

	private void cancelEditing() {
		qdtFields.forEach(qdtf -> qdtf.setEnabled(false));
		tellObserversOfCancelEditingEvent();
	}

	public void tellObserversOfCancelEditingEvent() {
		fireEvent(new EditingCancelledNotifier.Event<>(this, row));
	}

	private void setUnchanged() {
		save.setEnabled(false);
	}

	private <T, QDT extends QueryableDatatype<T>> void setChanged(QDTUpdateNotifier.Event<T, QDT> event) {
		final Object value = event.getUpdatedQDT().getValue();
		PropertyWrapper<?, T, QDT> propertyWrapper = event.getSource().getPropertyWrapper();
		Notification.show("New Value on " + propertyWrapper.javaName() + ": " + (value == null ? "<NULL>" : value.toString()));
		save.setEnabled(true);
	}

	private void addEditorListeners() {
		qdtFields.forEach(editor -> editor.addQDTUpdateListener(event -> setChanged(event)));
	}
}
