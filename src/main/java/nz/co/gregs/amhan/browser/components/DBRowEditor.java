/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import java.sql.SQLException;
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
public class DBRowEditor<ROW extends DBRow> extends Div {

	private final DBDatabase database;
	private final ROW row;
	private final Binder<ROW> binder;
	private final Button cancel = new Button("Cancel");
	private final Button save = new Button("Save");

	public DBRowEditor(DBDatabase database, ROW forThisRow, Binder<ROW> andThisBinder) {
		this.database = database;
		row = forThisRow;
		this.binder = andThisBinder;

		System.out.println("SET ROWEDITOR ROW: " + row);
		System.out.println("IS DEFINED:" + row.getDefined());

		initButtons();

		createEditorLayout(andThisBinder, row);
	}

	private void createEditorLayout(Binder<ROW> binder, ROW row) {
		setId("editor-layout");

		Div editorDiv = new Div();
		editorDiv.setId("editor");
		add(editorDiv);

		FormLayout formLayout = new FormLayout();
		addFields(formLayout, binder, row);

		add(formLayout);
		this.add(
				createButtonLayout());
	}

	private HorizontalLayout createButtonLayout() {
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setId("button-layout");
		buttonLayout.setWidthFull();
		buttonLayout.setSpacing(true);
		cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		buttonLayout.add(save, cancel);
		return buttonLayout;
	}

	private void addFields(FormLayout formLayout, Binder<ROW> binder, ROW row) {
		var qdts = row.getColumnQueryableDatatypes();
		qdts.forEach(qdt -> addQueryableDatatypeField(qdt, binder, row, formLayout));
	}

	private <T> void addQueryableDatatypeField(QueryableDatatype<T> qdt, Binder<ROW> binder, ROW row, FormLayout formLayout) {
		QDTComponentsBound<ROW, T> qdtComponent = QDTComponentsBound.getFor(row, qdt, binder);
		final AbstractField<?, T> editor = qdtComponent.getEditor();
		editor.getElement().getClassList().add("full-width");
		formLayout.add(editor);
	}

	private void initButtons() {
		initButtons(e -> saveTheRow());
	}

	private void saveTheRow() {
		try {
			Notification.show("Saving...");
			database.update(getRow());
			Notification.show("Save complete.");
		} catch (SQLException ex) {
			Notification.show("SAVE FAILED: " + ex.getLocalizedMessage());
			ex.printStackTrace();
			Logger.getLogger(DBRowEditor.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void initButtons(ComponentEventListener<ClickEvent<Button>> saveListener) {
		initButtons(saveListener, e -> {
			clearForm();
		});
	}

	private void initButtons(ComponentEventListener<ClickEvent<Button>> saveListener, ComponentEventListener<ClickEvent<Button>> cancelListener) {
		save.addClickListener(saveListener);
		cancel.addClickListener(cancelListener);
	}

	public void clearForm() {
		binder.readBean(null);
	}

	public void populateForm(ROW row) {
		binder.readBean(row);
	}

	public ROW getRow() {
		return row;
	}
}
