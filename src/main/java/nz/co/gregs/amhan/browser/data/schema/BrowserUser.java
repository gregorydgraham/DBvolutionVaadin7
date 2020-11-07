/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.data.schema;

import nz.co.gregs.dbvolution.DBRow;
import nz.co.gregs.dbvolution.annotations.DBAutoIncrement;
import nz.co.gregs.dbvolution.annotations.DBColumn;
import nz.co.gregs.dbvolution.annotations.DBPrimaryKey;
import nz.co.gregs.dbvolution.datatypes.DBInteger;
import nz.co.gregs.dbvolution.datatypes.DBLocalDate;
import nz.co.gregs.dbvolution.datatypes.DBPasswordHash;
import nz.co.gregs.dbvolution.datatypes.DBString;

/**
 *
 * @author gregorygraham
 */
public class BrowserUser extends DBRow {

	public BrowserUser() {
	}

	public BrowserUser(String username, String password) {
		this.username.setValue(username);
		this.password.setValue(password);
	}
	@DBColumn
	@DBAutoIncrement
	@DBPrimaryKey
	DBInteger pkid = new DBInteger();

	@DBColumn
	DBString username = new DBString();

	@DBColumn
	DBPasswordHash password = new DBPasswordHash();
	
	@DBColumn
	DBLocalDate signupDate = new DBLocalDate().setDefaultInsertValueToCurrentLocalDate();

}
