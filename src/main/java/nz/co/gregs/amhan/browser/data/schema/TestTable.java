/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.data.schema;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.annotations.DBAutoIncrement;
import nz.co.gregs.dbvolution.annotations.DBColumn;
import nz.co.gregs.dbvolution.annotations.DBForeignKey;
import nz.co.gregs.dbvolution.annotations.DBPrimaryKey;
import nz.co.gregs.dbvolution.datatypes.*;

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
	DBString text = new DBString();

	@DBColumn
	DBStringTrimmed trimmedText = new DBStringTrimmed();

	@DBColumn
	DBNumber rating = new DBNumber();

	@DBColumn
	DBUUID uniqueIdentifier = new DBUUID().setDefaultInsertValueRandomly();

	@DBColumn
	DBBooleanArray boolArray = new DBBooleanArray();

	public TestTable() {
	}
	
	public TestTable withOwner(BrowserUser user){
		this.userID.setValue(user.pkid);
		return this;
	}
	
	public TestTable postedAt(Instant instant){
		this.timePosted.setValue(instant);
		return this;
	}
	
	public TestTable updatedAt(LocalDateTime instant){
		this.timeUpdated.setValue(instant);
		return this;
	}
	
	public TestTable withText(String text){
		this.text.setValue(text);
		return this;
	}
	
	public TestTable withTrimmedText(String text){
		this.trimmedText.setValue(text);
		return this;
	}
	
	public TestTable withRating(Number rating){
		this.rating.setValue(rating);
		return this;
	}
	
	public TestTable hasNotBeenRead(){
		this.hasBeenRead.setValue(false);
		return this;
	}
	
	public TestTable hasBeenRead(){
		this.hasBeenRead.setValue(true);
		return this;
	}
	
	public TestTable withUniqueIdentifier(UUID uuid){
		this.uniqueIdentifier.setValue(uuid);
		return this;
	}
	
	public TestTable withBooleanArray(Boolean[] value){
		this.boolArray.setValue(value);
		return this;
	}
	
}
