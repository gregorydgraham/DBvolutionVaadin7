package nz.co.gregs.amhan.browser.views.about;

import nz.co.gregs.amhan.browser.security.PermissionDenied;
import nz.co.gregs.amhan.browser.security.LogonRequired;
import nz.co.gregs.amhan.browser.security.RestrictedComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import nz.co.gregs.amhan.browser.views.main.MainView;
import com.vaadin.flow.router.*;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
@CssImport("./styles/views/about/about-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class AboutView extends HorizontalLayout implements RestrictedComponent{

	private TextField name;
	private Button sayHello;

	public AboutView() {
		setId("about-view");
		name = new TextField("Your name");
		sayHello = new Button("Say hello");
		add(name, sayHello);
		setVerticalComponentAlignment(Alignment.END, name, sayHello);
		sayHello.addClickListener(e -> {
			Notification.show("Hello " + name.getValue());
		});
	}

	@Override
	public boolean checkForIdentity() {
		return true;
	}

	@Override
	public boolean checkForPermission() {
		return true;
	}

	@Override
	public void authorisedToEnter(BeforeEnterEvent event) {
		;
	}

	@Override
	public void notAuthorisedToEnter(BeforeEnterEvent event) {
		event.rerouteTo(PermissionDenied.class);
	}

	@Override
	public void notLoggedIn(BeforeEnterEvent event) {
		event.rerouteTo(LogonRequired.class);
	}

}
