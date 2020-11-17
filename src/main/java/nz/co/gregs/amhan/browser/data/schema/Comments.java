/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.data.schema;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.annotations.DBAutoIncrement;
import nz.co.gregs.dbvolution.annotations.DBColumn;
import nz.co.gregs.dbvolution.annotations.DBForeignKey;
import nz.co.gregs.dbvolution.annotations.DBPrimaryKey;
import nz.co.gregs.dbvolution.datatypes.DBInstant;
import nz.co.gregs.dbvolution.datatypes.DBInteger;
import nz.co.gregs.dbvolution.datatypes.DBLocalDateTime;
import nz.co.gregs.dbvolution.datatypes.DBString;

/**
 *
 * @author gregorygraham
 */
public class Comments extends DBRow {

	@DBAutoIncrement
	@DBColumn
	@DBPrimaryKey
	DBInteger pkid = new DBInteger();

	@DBColumn
	@DBForeignKey(BrowserUser.class)
	DBInteger userID = new DBInteger();

	@DBColumn
	DBInstant timePosted = new DBInstant().setDefaultInsertValueToNow();

	@DBColumn
	DBLocalDateTime timeUpdated = new DBLocalDateTime().setDefaultUpdateValueToNow();

	@DBColumn
	DBString text = new DBString();

	public Comments() {
	}
	
	public Comments withOwner(BrowserUser user){
		this.userID.setValue(user.pkid);
		return this;
	}
	
	public Comments postedAt(Instant instant){
		this.timePosted.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDateTime());
		return this;
	}
	
	public Comments updatedAt(LocalDateTime instant){
		this.timeUpdated.setValue(instant);
		return this;
	}
	
	public Comments withText(String text){
		this.text.setValue(text);
		return this;
	}
	
}
