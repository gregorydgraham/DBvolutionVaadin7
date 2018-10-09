/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.dbvolutionvaadin7.component;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.shared.Registration;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import nz.co.gregs.dbvolution.datatypes.DBLargeBinary;

public class DBLargeBinaryComponent extends HorizontalLayout {

	private final Document document;

	public DBLargeBinaryComponent(Document doc) {
		super();
		this.document = doc;
		if (doc.documentContents.isDefined()) {
			if (doc.documentContents.isNotNull()) {
				add(getFileIconComponent(doc));
				add(getDescriptionComponent(doc));
				add(getRemoveComponent(doc));
			}
		}
	}

	private Button getRemoveComponent(Document source) {
		return new Button(new Icon(VaadinIcon.RECYCLE), (event) -> removeDocument(source));
	}

	private Anchor getFileIconComponent(Document source) {
		Anchor anchor = new Anchor(new DocumentIconStreamResource(source), "");
		anchor.setTarget("_blank");
		Component icon;
		if (source.mediaType.startsWith("image/")) {
			icon = new Image(new DocumentIconStreamResource(source), source.filename);
		} else {
			icon = new Icon(VaadinIcon.FILE);
		}
		anchor.add(icon);
		return anchor;
	}

	private TextField getDescriptionComponent(Document source) {
		TextField component = new TextField(
				"",
				source.description == null ? (source.filename == null ? "..." : source.filename) : source.description,
				(event) -> {
					source.description = event.getValue();
				});
		component.setWidth("100%");
		return component;
	}

	private void removeDocument(Document img) {
		document.documentContents.setValueToNull();
		document.description = "";
		document.filename = "";
		document.mediaType = "";
	}

	public class DocumentUpload extends HorizontalLayout {

		MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
		Upload uploader = new Upload();

		public DocumentUpload() {
			super();
			uploader.setUploadButton(new Button("Add Document..."));
			uploader.setReceiver(buffer);
			uploader.addSucceededListener((event) -> {
				processSuccessfulUpload(event);
			});
			add(uploader);
		}

		private void processSuccessfulUpload(SucceededEvent event) {
			String fileName = event.getFileName();
			String mimeType = event.getMIMEType();
			System.out.println("fileID: " + fileName);
			Document doc = new Document();
			doc.mediaType = mimeType;
			doc.filename = fileName;
			final InputStream inputStream = buffer.getInputStream(fileName);
			doc.documentContents.setValue(inputStream);
			System.out.println("Document: " + doc.toString());
			fireEvent(new DocumentAddedEvent(this, true));
		}

		public Registration addDocumentAddedListener(
				ComponentEventListener<DocumentAddedEvent> listener) {
			return addListener(DocumentAddedEvent.class, listener);
		}
	}

	/**
	 *
	 * @author gregorygraham
	 */
	public static class Document {

		public DBLargeBinary documentContents = new DBLargeBinary();

		public String mediaType;

		public String filename;

		public String description;
	}

	public class DocumentAddedEvent extends ComponentEvent<DocumentUpload> {

		public DocumentAddedEvent(DocumentUpload source, boolean fromClient) {
			super(source, fromClient);
		}
	}

	public static class DocumentIconStreamResource extends StreamResource {

		public DocumentIconStreamResource(Document doc) {
			super(doc.filename, new ThumbnailInputStreamFactory(doc));
		}

		public static class ThumbnailInputStreamFactory implements InputStreamFactory {

			private final Document doc;

			public ThumbnailInputStreamFactory(Document doc) {
				this.doc = doc;
			}

			@Override
			public InputStream createInputStream() {
				try {
					BufferedImage thumbnail = createThumbnailFromOriginalRow();
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					ImageIO.write(thumbnail, "png", os);
					InputStream fis = new ByteArrayInputStream(os.toByteArray());
					return fis;
				} catch (IOException ex) {
					Logger.getLogger(DocumentIconStreamResource.class.getName()).log(Level.SEVERE, null, ex);
				}
				return new ByteArrayInputStream(new byte[]{});
			}

			private BufferedImage createThumbnailFromOriginalRow() throws IOException {
				final InputStream inputStream = doc.documentContents.getInputStream();
				BufferedImage originalImage = ImageIO.read(inputStream);
				BufferedImage thumbnail = createThumbnail(originalImage);
				return thumbnail;
			}

			private BufferedImage createThumbnail(BufferedImage original) {
				final double targetSize = 50d;
				double scale = Math.min(targetSize / original.getWidth(), targetSize / original.getHeight());
				final int width = (int) ((0d + original.getWidth()) * scale);
				final int height = (int) ((0d + original.getHeight()) * scale);
				BufferedImage thumbnail = new BufferedImage(width, height, original.getType());
				Graphics2D g = thumbnail.createGraphics();
				g.drawImage(original, 0, 0, width, height, null);
				g.dispose();
				return thumbnail;
			}
		}
	}
}
