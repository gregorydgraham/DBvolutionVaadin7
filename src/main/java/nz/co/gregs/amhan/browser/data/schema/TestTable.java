/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.data.schema;

import java.time.Instant;
import java.time.LocalDateTime;
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
	DBBoolean read = new DBBoolean().setDefaultInsertValue(Boolean.FALSE);

	@DBColumn
	DBInstant timePosted = new DBInstant().setDefaultInsertValueToNow();

	@DBColumn
	DBLocalDate dateUpdated = new DBLocalDate().setDefaultUpdateValueToCurrentLocalDate();

	@DBColumn
	DBLocalDateTime timeUpdated = new DBLocalDateTime().setDefaultUpdateValueToNow();

	@DBColumn
	DBString text = new DBString();

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
	
	public TestTable notRead(){
		this.read.setValue(false);
		return this;
	}
	
	public TestTable read(){
		this.read.setValue(true);
		return this;
	}
	
}
