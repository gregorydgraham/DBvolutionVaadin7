/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.data.schema;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.annotations.DBAutoIncrement;
import nz.co.gregs.dbvolution.annotations.DBColumn;
import nz.co.gregs.dbvolution.annotations.DBForeignKey;
import nz.co.gregs.dbvolution.annotations.DBPrimaryKey;
import nz.co.gregs.dbvolution.columns.ColumnProvider;
import nz.co.gregs.dbvolution.columns.NumberColumn;
import nz.co.gregs.dbvolution.databases.definitions.DBDefinition;
import nz.co.gregs.dbvolution.datatypes.*;
import nz.co.gregs.dbvolution.exceptions.CannotEncryptInputException;
import nz.co.gregs.dbvolution.exceptions.IncorrectRowProviderInstanceSuppliedException;
import nz.co.gregs.dbvolution.query.RowDefinition;
import nz.co.gregs.dbvolution.utility.encryption.Encrypted;

/**
 *
 * @author gregorygraham
 */
public class TestTable extends DBRow {

	@DBAutoIncrement
	@DBColumn
	@DBPrimaryKey
	DBInteger pkid = new DBInteger();

	@DBColumn
	@DBForeignKey(BrowserUser.class)
	DBInteger userID = new DBInteger();

	@DBColumn
	DBBoolean hasBeenRead = new DBBoolean().setDefaultInsertValue(Boolean.FALSE);

	@DBColumn
	DBInstant timePosted = new DBInstant().setDefaultInsertValueToNow();

	@DBColumn
	DBLocalDate dateUpdated = new DBLocalDate().setDefaultUpdateValueToCurrentLocalDate();

	@DBColumn
	DBLocalDateTime timeUpdated = new DBLocalDateTime().setDefaultUpdateValueToNow();

	@DBColumn
	DBDateOnly dateOnly = new DBDateOnly().setDefaultInsertValueToCurrentDateOnly();

	@DBColumn
	DBString text = new DBString();

	@DBColumn
	DBStringTrimmed trimmedText = new DBStringTrimmed();

	@DBColumn
	DBNumber rating = new DBNumber();

	@DBColumn
	DBUUID uniqueIdentifier = new DBUUID().setDefaultInsertValueRandomly();

	@DBColumn
	DBBooleanArray boolArray = new DBBooleanArray();

	@DBColumn
	DBEncryptedText encryptedText = new DBEncryptedText();

	@DBColumn
	DBIntegerEnum<IntegerEnum> integerEnum = new DBIntegerEnum<>();

	@DBColumn
	DBStringEnum<StringEnum> stringEnum = new DBStringEnum<>();

	@DBColumn
	DBDate date = new DBDate();

	@DBColumn
	DBDoubleEnum<DoubleEnum> doubleEnum = new DBDoubleEnum<>();

	@DBColumn
	DBLargeText largeText = new DBLargeText();
	
	@DBColumn
	DBLargeBinary largeBinary = new DBLargeBinary();
	
	@DBColumn
	DBJavaObject<SomeClass> javaObject = new DBJavaObject<>();
	
	@DBColumn
	DBDuration duration = new DBDuration();

	public TestTable() {
	}

	public TestTable withOwner(BrowserUser user) {
		this.userID.setValue(user.pkid);
		return this;
	}

	public TestTable postedAt(Instant instant) {
		this.timePosted.setValue(instant);
		return this;
	}

	public TestTable updatedAt(LocalDateTime instant) {
		this.timeUpdated.setValue(instant);
		return this;
	}

	public TestTable withText(String text) {
		this.text.setValue(text);
		return this;
	}

	public TestTable withTrimmedText(String text) {
		this.trimmedText.setValue(text);
		return this;
	}

	public TestTable withRating(Number rating) {
		this.rating.setValue(rating);
		return this;
	}

	public TestTable hasNotBeenRead() {
		this.hasBeenRead.setValue(false);
		return this;
	}

	public TestTable hasBeenRead() {
		this.hasBeenRead.setValue(true);
		return this;
	}

	public TestTable withUniqueIdentifier(UUID uuid) {
		this.uniqueIdentifier.setValue(uuid);
		return this;
	}

	public TestTable withBooleanArray(Boolean[] value) {
		this.boolArray.setValue(value);
		return this;
	}

	public TestTable withEncryptedText(String passphrase, String text) throws CannotEncryptInputException {
		this.encryptedText.setValue(Encrypted.encrypt(passphrase, text));
		return this;
	}

	public TestTable withIntegerEnum(IntegerEnum value) {
		this.integerEnum.setValue(value);
		return this;
	}

	public TestTable withStringEnum(StringEnum value) {
		this.stringEnum.setValue(value);
		return this;
	}

	public TestTable withDate(Date value) {
		this.date.setValue(value);
		return this;
	}

	public TestTable withDoubleEnum(DoubleEnum value) {
		this.doubleEnum.setValue(value);
		return this;
	}

	public TestTable withLargeText(String value) {
		this.largeText.setValue(value);
		return this;
	}

	public TestTable withLargeBinary(String value) {
		this.largeBinary.setValue(value);
		return this;
	}
	
	public TestTable withLargeBinary(File value) throws IOException {
		this.largeBinary.setValue(value);
		return this;
	}
	
	public TestTable withJavaObject(SomeClass value) throws IOException {
		this.javaObject.setValue(value);
		return this;
	}
	
	public TestTable withDuration(Duration value) throws IOException {
		this.duration.setValue(value);
		return this;
	}

	public static class DBDoubleEnum<E extends Enum<E> & DBEnumValue<Double>> extends DBEnum<E, Double> {

		public DBDoubleEnum() {
		}

		@Override
		protected void validateLiteralValue(E enumValue) throws IncompatibleClassChangeError {
			Object localValue = enumValue.getCode();
			if (localValue != null) {
				if (!(localValue instanceof Double)) {
					String enumMethodRef = enumValue.getClass().getName() + "." + enumValue.name() + ".getLiteralValue()";
					String literalValueTypeRef = localValue.getClass().getName();
					throw new IncompatibleClassChangeError("Enum literal type is not valid: "
							+ enumMethodRef + " returned a " + literalValueTypeRef + ", which is not valid for a " + this.getClass().getSimpleName());
				}
			}
		}

		@Override
		protected void setValueFromStandardStringEncoding(String encodedValue) {
			setValue(Double.parseDouble(encodedValue));
		}

		@Override
		public String getSQLDatatype() {
			return new DBNumber().getSQLDatatype();
		}

		@Override
		protected Double getFromResultSet(DBDefinition database, ResultSet resultSet, String fullColumnName) throws SQLException {
			return resultSet.getDouble(fullColumnName);
		}

		@Override
		public ColumnProvider getColumn(RowDefinition row) throws IncorrectRowProviderInstanceSuppliedException {
			return new NumberColumn(row, this);
		}
	}
	
	
	public static class SomeClass implements Serializable {

		private static final long serialVersionUID = 1L;
		public String str;
		public int integer;

		public SomeClass(int integer, String str) {
			this.str = str;
			this.integer = integer;
		}
	}

}
