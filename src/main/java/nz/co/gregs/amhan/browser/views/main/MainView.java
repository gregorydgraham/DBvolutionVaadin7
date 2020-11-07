package nz.co.gregs.amhan.browser.views.main;

import nz.co.gregs.amhan.browser.data.schema.BrowserUser;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.Theme;
import nz.co.gregs.amhan.browser.views.about.AboutView;
import nz.co.gregs.amhan.browser.views.tables.TablesView;
import nz.co.gregs.amhan.browser.views.quickquery.QuickQueryView;
import nz.co.gregs.amhan.browser.views.selecttable.SelectTableView;
import nz.co.gregs.amhan.browser.views.selecttable.ShowRows;
import com.vaadin.flow.theme.lumo.Lumo;
import nz.co.gregs.dbvolution.DBRow;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@CssImport("./styles/views/main/main-view.css")
public class MainView extends AppLayout {

	private final Tabs menu;
	private H1 viewTitle;

	public MainView() {
		setPrimarySection(Section.DRAWER);
		addToNavbar(true, createHeaderContent());
		menu = createMenu();
		addToDrawer(createDrawerContent(menu));
	}

	private Component createHeaderContent() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setId("header");
		layout.getThemeList().set("dark", true);
		layout.setWidthFull();
		layout.setSpacing(false);
		layout.setAlignItems(FlexComponent.Alignment.CENTER);
		layout.add(new DrawerToggle());
		viewTitle = new H1();
		layout.add(viewTitle);
		layout.add(new Image("images/user.svg", "Avatar"));
		return layout;
	}

	private Component createDrawerContent(Tabs menu) {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setPadding(false);
		layout.setSpacing(false);
		layout.getThemeList().set("spacing-s", true);
		layout.setAlignItems(FlexComponent.Alignment.STRETCH);
		HorizontalLayout logoLayout = new HorizontalLayout();
		logoLayout.setId("logo");
		logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
		logoLayout.add(new Image("images/logo.png", "Amhan Database Browser logo"));
		logoLayout.add(new H1("Amhan Database Browser"));
		layout.add(logoLayout, menu);
		return layout;
	}

	private Tabs createMenu() {
		final Tabs tabs = new Tabs();
		tabs.setOrientation(Tabs.Orientation.VERTICAL);
		tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
		tabs.setId("tabs");
		tabs.add(createMenuItems());
		return tabs;
	}

	private Component[] createMenuItems() {
		return new Tab[]{
			createTab("About", AboutView.class),
			createTab("Select Table", SelectTableView.class),
			createTab("Show Rows", ShowRows.class, "nz.co.gregs.amhan.browser.data.schema.Comments"),
			createTab("Tables", TablesView.class),
			createTab("Quick Query", QuickQueryView.class)
		};
	}

	private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
		final Tab tab = new Tab();
		tab.add(new RouterLink(text, navigationTarget));
		ComponentUtil.setData(tab, Class.class, navigationTarget);
		return tab;
	}

	private static <T, C extends Component & HasUrlParameter<T>> Tab createTab(String text, Class<? extends C> navigationTarget, T params) {
		final Tab tab = new Tab();
		tab.add(new RouterLink(text, navigationTarget, params));
		ComponentUtil.setData(tab, Class.class, navigationTarget);
		return tab;
	}

	@Override
	protected void afterNavigation() {
		super.afterNavigation();
		getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
		viewTitle.setText(getCurrentPageTitle());
	}

	private Optional<Tab> getTabForComponent(Component component) {
		return menu.getChildren()
				.filter(tab -> ComponentUtil.getData(tab, Class.class)
				.equals(component.getClass()))
				.findFirst().map(Tab.class::cast);
	}

	private String getCurrentPageTitle() {
		final Component content = getContent();
		if (content != null) {
			if (content instanceof HasDynamicTitle) {
				return ((HasDynamicTitle) content).getPageTitle();
			} else {
				final Class<? extends Component> aClass = content.getClass();
				if (aClass != null) {
					final PageTitle annotation = aClass.getAnnotation(PageTitle.class);
					if (annotation != null) {
						return annotation.value();
					} else {
						return "Please Define @PageTitle for " + aClass.getSimpleName();
					}
				}
			}
		}
		return AboutView.class.getAnnotation(PageTitle.class).value();
	}
}
