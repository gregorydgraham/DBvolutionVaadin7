/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.server.StreamResource;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBLargeBinary;
import org.vaadin.olli.FileDownloadWrapper;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBLargeBinaryField<ROW extends DBRow> extends QueryableDatatypeField<ROW, byte[], DBLargeBinary> {

	private MemoryBuffer buffer;
	private Upload upload;
	private Label sizeIndicator;
	private FileDownloadWrapper downloadButton;

	public DBLargeBinaryField(ROW row, DBLargeBinary qdt) {
		super(null, row, qdt);
		setContentType(ContentType.APPLICATION_OCTET_STREAM);
		setFilename("download.bin");
		setDownloadButton();
	}

	@Override
	protected final void setPresentationValue(byte[] value) {
		if (value != null) {
			sizeIndicator.setText(getQueryableDatatype().getSizeAsReadableString());
		} else {
			sizeIndicator.setText("Currently Empty");
		}
	}

	@Override
	protected void addInternalComponents(DBLargeBinary qdt) {
		add(new Span(downloadButton, sizeIndicator), new Details("Attach new binary", upload));
	}

	@Override
	protected void createInternalComponents() {
		buffer = new MemoryBuffer();
		upload = new Upload(buffer);
		upload.setMaxFiles(1);
		upload.setUploadButton(new Button("Upload File...", VaadinIcon.UPLOAD.create()));
		sizeIndicator = new Label();

		downloadButton = new FileDownloadWrapper("download.bin", new FileDownloadWrapper.DownloadBytesProvider() {
			@Override
			public byte[] getBytes() {
				return getQueryableDatatype().getBytes();
			}
		});
	}

	@Override
	protected void addInternalValueChangeListeners() {
		upload.addSucceededListener(e -> updateOnSuccess(e));
	}

	private void updateOnSuccess(SucceededEvent event) {
		try {
			final InputStream inputStream = buffer.getInputStream();
			byte[] allBytes = inputStream.readAllBytes();
			sizeIndicator.setText("" + allBytes.length + "bytes - " + event.getFileName());
			updateQDT(allBytes);
		} catch (IOException ex) {
			Notification.show("IOException: " + ex.getLocalizedMessage());
			Logger.getLogger(DBLargeBinaryField.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void setDownloadButton() {
		final StreamResource streamResource = new StreamResource(getFilename(), () -> new ByteArrayInputStream(getQueryableDatatype().getBytes()));
		streamResource.setContentType(getContentType());
		streamResource.setCacheTime(0);
		downloadButton.setResource(streamResource);

		Button button = new Button("Download");
		button.setIcon(VaadinIcon.DOWNLOAD.create());
		downloadButton.wrapComponent(button);
	}

	public String getFilename() {
		return filename;
	}

	public final void setFilename(String nameOfTheFile) {
		filename = nameOfTheFile;
		setDownloadButton();
	}

	private String filename = "download.bin";

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String internetMimeType) {
		contentType = internetMimeType;
		setDownloadButton();
	}

	public final void setContentType(ContentType internetMimeType) {
		setContentType(internetMimeType.getCode());
	}

	private String contentType = ContentType.APPLICATION_OCTET_STREAM.getCode();

	public static enum ContentType {

		APPLICATION_OCTET_STREAM("application/octet-stream"),
		BITMAP("image/bmp"),
		CSS("text/css"),
		CSV("text/csv"),
		MSWORD("application/msword"),
		MSWORDXML("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
		GIF("image/gif"),
		HTML("text/html"),
		ICAL("text/calendar"),
		JAR("application/java-archive"),
		JPEG("image/jpeg"),
		JAVASCRIPT("text/javascript"),
		JSON("application/json"),
		MIDI("audio/midi"),
		MP3("audio/mpeg"),
		MPEG("video/mpeg"),
		PNG("image/png"),
		PDF("application/pdf"),
		POWERPOINTXML("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
		TAR("application/x-tar"),
		TIFF("image/tiff"),
		TEXT("text/plain"),
		WEBMAUDIO("audio/webm"),
		WEBMVIDEO("video/webm"),
		WEMPIMAGE("image/webp"),
		MSEXCELXML("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
		ZIP("application/zip"),
		SEVENZIP("application/x-7z-compressed");

		private final String code;

		ContentType(String internetMimeType) {
			this.code = internetMimeType;
		}

		public String getCode() {
			return code;
		}
	}
}
