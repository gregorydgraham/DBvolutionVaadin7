/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.databases.DBDatabase;
import nz.co.gregs.dbvolution.datatypes.QueryableDatatype;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBRowEditor<ROW extends DBRow> extends Composite<Div> implements DBRowUpdateNotifier<ROW>, EditingCancelledNotifier<ROW> {
	
	private final DBDatabase database;
	private final ROW row;
	private final Button cancel = new Button("Cancel");
	private final Button save = new Button("Save");
	private final List<QueryableDatatypeField> qdtFields = new ArrayList<>();
	
	public DBRowEditor(DBDatabase database, ROW forThisRow) {
		this.database = database;
		row = DBRow.copyDBRow(forThisRow);
		
		initButtons();
		
		createEditorLayout(row);
	}
	
	private void createEditorLayout(ROW row) {
		setId("editor-layout");
		
		Div editorDiv = new Div();
		editorDiv.setId("editor");
		getContent().add(editorDiv);
		
		FormLayout formLayout = new FormLayout();
		addFields(formLayout, row);
		
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
		save.setEnabled(false);
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
		editor.addQDTUpdateListener(event -> enableSaveButton());
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
			Logger.getLogger(DBRowEditor.class.getName()).log(Level.SEVERE, null, ex);
		}
		database.setPrintSQLBeforeExecuting(false);
	}
	
	private void initButtons() {
		initButtons(e -> saveTheRow());
	}
	
	private void initButtons(ComponentEventListener<ClickEvent<Button>> saveListener) {
		initButtons(saveListener, e -> {
			Notification.show("Cancelled");
		});
	}
	
	private void initButtons(ComponentEventListener<ClickEvent<Button>> saveListener, ComponentEventListener<ClickEvent<Button>> cancelListener) {
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
		qdtFields.stream().forEach(f->f.reloadValue());
	}

	private void enableSaveButton() {
		save.setEnabled(true);
	}

	private void cancelEditing() {
		qdtFields.forEach(qdtf->qdtf.setEnabled(false));
		tellObserversOfCancelEditingEvent();
	}
	
	public void tellObserversOfCancelEditingEvent() {
		fireEvent(new EditingCancelledNotifier.Event<>(this,row));
	}
}
