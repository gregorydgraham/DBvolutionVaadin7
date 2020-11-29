/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.server.StreamResource;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBJavaObject;
import nz.co.gregs.dbvolution.datatypes.DBLargeText;
import org.vaadin.olli.FileDownloadWrapper;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBJavaObjectField<ROW extends DBRow, OBJECT> extends QueryableDatatypeField<ROW, OBJECT, DBJavaObject<OBJECT>> {

	private Label sizeIndicator;
	private FileDownloadWrapper downloadButton;
	private TextArea textOfJavaObject;

	public DBJavaObjectField(ROW row, DBJavaObject<OBJECT> qdt) {
		super(null, row, qdt);
		setDownloadButton();
	}

	@Override
	protected final void setPresentationValue(OBJECT value) {
		if (value != null) {
			sizeIndicator.setText(getQueryableDatatype().getSizeAsReadableString());
		} else {
			sizeIndicator.setText("Currently Empty");
		}
	}

	@Override
	protected void addInternalComponents(DBJavaObject<OBJECT> qdt) {
		add(textOfJavaObject, new Span(downloadButton, sizeIndicator));
	}

	@Override
	protected void createInternalComponents() {
		sizeIndicator = new Label();
		textOfJavaObject = new TextArea();
		textOfJavaObject.setValue(DBLargeText.transformToStandardCharset(getBytes()));

		downloadButton = new FileDownloadWrapper("download.text", new FileDownloadWrapper.DownloadBytesProvider() {
			@Override
			public byte[] getBytes() {
				return getBytes();
			}
		});
	}

	@Override
	protected void addInternalValueChangeListeners() {
		textOfJavaObject.addValueChangeListener(e -> updateOnEdit(e));
	}

	private void setDownloadButton() {
		final StreamResource streamResource = new StreamResource("download.text", () -> getBytesArrayInputStream());
		streamResource.setContentType(DBLargeBinaryField.ContentType.TEXT.getCode());
		streamResource.setCacheTime(0);
		downloadButton.setResource(streamResource);

		Button button = new Button("Download");
		button.setIcon(VaadinIcon.DOWNLOAD.create());
		downloadButton.wrapComponent(button);
	}

	private ByteArrayInputStream getBytesArrayInputStream() {
		return new ByteArrayInputStream(getBytes());
	}

	private byte[] getBytes() {
		try {
			return getQueryableDatatype().getBytes();
		} catch (IOException ex) {
			Logger.getLogger(DBJavaObjectField.class.getName()).log(Level.SEVERE, null, ex);
		}
		return new byte[]{};
	}

	@SuppressWarnings("unchecked")
	private void updateOnEdit(AbstractField.ComponentValueChangeEvent<TextArea, String> e) {
		OBJECT object = (OBJECT) textOfJavaObject.getValue().getBytes();
		updateQDT(object);
	}
}
