/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.components;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.datatypes.DBEncryptedText;
import nz.co.gregs.dbvolution.exceptions.CannotEncryptInputException;
import nz.co.gregs.dbvolution.exceptions.UnableToDecryptInput;
import nz.co.gregs.dbvolution.utility.encryption.Encrypted;

/**
 *
 * @author gregorygraham
 * @param <ROW>
 */
public class DBEncryptedTextField<ROW extends DBRow> extends QueryableDatatypeField<ROW, byte[], DBEncryptedText> {

	PasswordField passPhraseField;
	TextArea textfield;
	TextArea encryptedTextField;
	Label warningField;
	private boolean decrypting;

	public DBEncryptedTextField(ROW row, DBEncryptedText qdt) {
		super(new byte[]{}, row, qdt);
	}

	@Override
	protected final void setPresentationValue(byte[] value) {
		if (value != null) {
			encryptedTextField.setValue(new String(value, StandardCharsets.UTF_8));
			final String passPhraseText = passPhraseField.getValue();
			if (passPhraseText != null && !passPhraseText.isEmpty()) {
				try {
					Encrypted.fromCipherText(value).decrypt(passPhraseText);
				} catch (UnableToDecryptInput ex) {
					setWarning(ex);
				}
			}
		} else {
			textfield.clear();
		}
	}

	private void setWarning(Exception ex) {
		warningField.setText(ex.getLocalizedMessage());
		Logger.getLogger(DBEncryptedTextField.class.getName()).log(Level.WARNING, null, ex);
	}

	@Override
	protected void addInternalComponents(DBEncryptedText qdt) {
		add(new Div(new Label("Plaintext"), textfield));
		add(new Div(new Label("Passphrase"), passPhraseField));
		add(new Div(new Label("Ciphertext"), encryptedTextField));
		add(new Div(warningField));
	}

	@Override
	protected void createInternalComponents() {
		textfield = new TextArea();
		passPhraseField = new PasswordField();
		warningField = new Label();
		encryptedTextField = new TextArea();
		encryptedTextField.setReadOnly(true);
	}

	@Override
	protected void addInternalValueChangeListeners() {
		textfield.addValueChangeListener(e -> updateSourceText(e));
		passPhraseField.addValueChangeListener(e -> updatePassPhrase(e));
	}

	private void updateSourceText(ComponentValueChangeEvent<TextArea, String> e) {
		if (isEncrypting()) {
			String sourceText = textfield.getValue();
			if (sourceText != null && !sourceText.isEmpty()) {
				String passPhrase = passPhraseField.getValue();
				if (passPhrase != null && !passPhrase.isEmpty()) {
					try {
						final Encrypted encrypt = Encrypted.encrypt(passPhrase, sourceText);
						encryptedTextField.setValue(encrypt.getCypherText());
						updateQDT(encrypt.getBytes());
						warningField.setText("");
					} catch (CannotEncryptInputException ex) {
						warningField.setText(ex.getLocalizedMessage());
						Logger.getLogger(DBEncryptedTextField.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}
	}

	private void updatePassPhrase(AbstractField.ComponentValueChangeEvent<PasswordField, String> e) {
		String passPhrase = passPhraseField.getValue();
		if (passPhrase != null && !passPhrase.isEmpty()) {
			String sourceText = textfield.getValue();
			if (sourceText != null && !sourceText.isEmpty()) {
				try {
					final Encrypted encrypt = Encrypted.encrypt(passPhrase, sourceText);
					encryptedTextField.setValue(encrypt.getCypherText());
					updateQDT(encrypt.getBytes());
					warningField.setText("");
				} catch (CannotEncryptInputException ex) {
					warningField.setText(ex.getLocalizedMessage());
					Logger.getLogger(DBEncryptedTextField.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				String cipherText = encryptedTextField.getValue();
				if (cipherText != null && !cipherText.isEmpty()) {
					doDecrypting(cipherText, passPhrase);
				}
			}
		}
	}

	private void doDecrypting(String cipherText, String passPhrase) {
		try {
			startDecrypting();
			final String decrypt = Encrypted.fromCipherText(cipherText).decrypt(passPhrase);
			textfield.setValue(decrypt);
			warningField.setText("");
		} catch (UnableToDecryptInput ex) {
			warningField.setText(ex.getLocalizedMessage());
			Logger.getLogger(DBEncryptedTextField.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			finishDecrypting();
		}
	}

	private synchronized void finishDecrypting() {
		decrypting = false;
	}

	private synchronized void startDecrypting() {
		decrypting = true;
	}

	private synchronized boolean isEncrypting() {
		return !decrypting;
	}

}
