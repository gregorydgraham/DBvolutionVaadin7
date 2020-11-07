package nz.co.gregs.amhan.browser.views.selecttable;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ComponentEventListener;
import nz.co.gregs.amhan.browser.components.DBRowEditor;
import nz.co.gregs.amhan.browser.grid.DBTableGrid;
import nz.co.gregs.amhan.browser.data.Database;
import nz.co.gregs.amhan.browser.data.schema.BrowserUser;
import nz.co.gregs.amhan.browser.security.RestrictedComponent;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import nz.co.gregs.amhan.browser.views.main.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nz.co.gregs.amhan.browser.components.DBRowUpdateNotifier;
import nz.co.gregs.dbvolution.DBRow;

@Route(value = "showrows", layout = MainView.class)
@CssImport("./styles/views/showrows/show-rows-view.css")
public class ShowRows extends Div implements RestrictedComponent, HasDynamicTitle, HasUrlParameter<String> {

	private static final Logger LOG = Logger.getLogger(ShowRows.class.getCanonicalName());

	private final Database database;
	private String className;
	private DBRow example;

	private DBTableGrid<DBRow> grid;
	private Binder<DBRow> binder;
	private Div wrapper;
	private Div secondaryPaneContents;
	private final SplitLayout splitLayout = new SplitLayout();

	@Override
	public void setParameter(BeforeEvent event, String canonicalClassName) {
		LOG.log(Level.INFO, "PARAMETER: {0}", canonicalClassName == null ? "NULL" : canonicalClassName);
		className = canonicalClassName;
		LOG.log(Level.INFO, "CLASSNAME: {0}", className == null ? "NULL" : className);
		setDBRowExample();
	}

	@SuppressWarnings("unchecked")
	public ShowRows(@Autowired Database db) {
		setId("quick-query-view");
		addWindowResizeListener();
		this.database = db;
		setDBRowExample();
		example = getDBRowExample();

		splitLayout.setSizeFull();

		createGridLayout(this.database, splitLayout);

		add(splitLayout);
	}

	@SuppressWarnings("unchecked")
	private void configureGridAndBinder(Database db) {

		final DBRow row = getDBRowExample();
		grid = new DBTableGrid<>(db, row);

		// when a row is selected or deselected, populate form
		grid.asSingleSelect().addValueChangeListener(event -> configureFormAfterValueChangeEvent(row, event));
	}

	@SuppressWarnings("unchecked")
	private void configureFormAfterValueChangeEvent(final DBRow oldValue, AbstractField.ComponentValueChangeEvent<Grid<DBRow>, DBRow> event) {
		DBRow newValue = event.getValue();
		if (newValue != null) {
			binder = new Binder<DBRow>((Class<DBRow>) newValue.getClass());

			if (secondaryPaneContents != null) {
				splitLayout.remove(secondaryPaneContents);
			}
			secondaryPaneContents = createEditorLayout(newValue, binder);
			splitLayout.addToSecondary(secondaryPaneContents);
			populateForm(event.getValue());
		}
	}

	private Div createEditorLayout(DBRow row, Binder<DBRow> binder) {

		final DBRowEditor<DBRow> rowEditor = new DBRowEditor<>(database, row, binder);
		rowEditor.addDBRowUpdatedListener(new ComponentEventListener<DBRowUpdateNotifier.DBRowUpdatedEvent<DBRow>>() {
			@Override
			public void onComponentEvent(DBRowUpdateNotifier.DBRowUpdatedEvent<DBRow> event) {
				grid.refreshItem(event.getUpdatedRow());
			}
		});
		return rowEditor;
	}

	private void addWindowResizeListener() {
		UI.getCurrent().getPage().addBrowserWindowResizeListener(
				event -> Notification.show("Window width="
						+ event.getWidth()
						+ ", height=" + event.getHeight()));
	}

	private void createGridLayout(Database db, SplitLayout splitLayout) {
		wrapper = new Div();
		wrapper.setId("grid-wrapper");
		wrapper.setWidthFull();
		configureGridAndBinder(db);
		wrapper.add(grid);
		splitLayout.addToPrimary(wrapper);
	}

	private void resetGrid() {
		if (wrapper != null) {
			if (grid != null) {
				wrapper.remove(grid);
			}
			if (database != null) {
				configureGridAndBinder(database);
			}
			wrapper.add(grid);
		}
		refreshGrid();
	}

	private void refreshGrid() {
		if (grid != null) {
			grid.select(null);
			grid.getDataProvider().refreshAll();
		}
	}

	private void populateForm(DBRow value) {
		binder.readBean(value);
	}

	@Override
	public String getPageTitle() {
		return "Show Rows of " + getDBRowExample().getTableName();
	}

	private DBRow getDBRowExample() {
		return example;
	}

	@SuppressWarnings("unchecked")
	private void setDBRowExample() {
		try {
			if (className != null) {
				LOG.log(Level.INFO, "className is:{0}", className);
				if (example == null) {
					LOG.info("example is null");
					getDBRowInstanceAndAssignToExample();
				} else if (!example.getClass().getCanonicalName().equals(className)) {
					LOG.info("example is not the same as className");
					getDBRowInstanceAndAssignToExample();
				}
			}
			if (example == null) {
				LOG.info("example is now: NULL");
			} else {
				LOG.log(Level.INFO, "example is now:{0}", example.getClass().getCanonicalName());
			}
		} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException exc) {
			LOG.log(Level.WARNING, "Unable to instantiate class:{0}", className);
			LOG.warning(exc.getLocalizedMessage());
			exc.printStackTrace();
		}
		if (example == null) {
			example = new BrowserUser();
			LOG.log(Level.INFO, "defaulted example to class:{0}", example.getClass().getCanonicalName());
		}
		resetGrid();
	}

	@SuppressWarnings("unchecked")
	private void getDBRowInstanceAndAssignToExample() throws NoSuchMethodException, SecurityException, InvocationTargetException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InstantiationException {
		Class<?> clazz = Class.forName(className);
		if (DBRow.class.isAssignableFrom(clazz)) {
			Constructor<?> constructor = clazz.getDeclaredConstructor();
			LOG.log(Level.INFO, "number of constructors:{0}", constructor);
			if (constructor != null) {
				example = (DBRow) constructor.newInstance();
				LOG.log(Level.INFO, "set example to class:{0}", className);
			}
		}
	}
}
