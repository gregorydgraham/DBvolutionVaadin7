/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.data;

import nz.co.gregs.amhan.browser.data.schema.BrowserUser;
import nz.co.gregs.amhan.browser.data.schema.Comments;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;
import nz.co.gregs.amhan.browser.data.schema.TestTable;
import nz.co.gregs.dbvolution.exceptions.CannotEncryptInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author gregorygraham
 */
@SpringComponent
public class DataLoader {

	@Bean
	public CommandLineRunner loadDatabase(Database dbRepository) {
		return args -> {
			Logger logger = LoggerFactory.getLogger(getClass());
			// all of this could possibly be done in the Database constructor
			// but this does avoid any issues with unfinished initialisation
			setData(dbRepository);

			logger.info("Generated demo database schema");
			return;
		};
	}

	private void setData(Database db) throws SQLException, CannotEncryptInputException {
		db.createTable(new BrowserUser());
		db.createTable(new Comments());
		db.createTable(new TestTable());
		final BrowserUser alice = new BrowserUser("alice", "password");

		db.insert(alice,
				new BrowserUser("bart5", "password"),
				new BrowserUser("cindy", "password"),
				new BrowserUser("denny", "password"),
				new BrowserUser("eruditeOtter", "shibboleth")
		);

		db.insert(
				new Comments().withOwner(alice).postedAt(Instant.now()).withText("Great website"),
				new Comments().withOwner(alice).withText("Is anyone else here?")
		);

		db.insert(
				new TestTable()
						.withOwner(alice)
						.postedAt(Instant.now())
						.withText("Great website")
						.hasBeenRead()
						.withTrimmedText("  no spaces here   ")
						.withRating(2.056)
						.withUniqueIdentifier(UUID.randomUUID())
						.withBooleanArray(new Boolean[]{false, false, false}),
				new TestTable()
						.withOwner(alice)
						.withText("Is anyone else here?")
						.hasNotBeenRead()
						.withRating(-1.53)
						.withBooleanArray(new Boolean[]{true, true, true, true, true})
						.withEncryptedText("secret secret", "very secret text")
		);
	}

}
