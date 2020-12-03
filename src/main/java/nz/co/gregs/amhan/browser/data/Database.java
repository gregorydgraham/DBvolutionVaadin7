/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.data;

import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.gregs.dbvolution.databases.DBDatabaseHandle;
import nz.co.gregs.dbvolution.databases.settingsbuilders.SQLiteSettingsBuilder;
import org.springframework.stereotype.Service;

/**
 *
 * @author gregorygraham
 */
@Service
public class Database extends DBDatabaseHandle{

	public Database() {
		super();
		try {
			this.setDatabase(new SQLiteSettingsBuilder().setFilename("amhanBrowser.sqlite").getDBDatabase());
		} catch (Exception ex) {
			Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
