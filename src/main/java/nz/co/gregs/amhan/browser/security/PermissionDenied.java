/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.security;

import nz.co.gregs.amhan.browser.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *
 * @author gregorygraham
 */
@Route(value = "permissiondenied", layout = MainView.class)
@PageTitle("Denied")
@CssImport("./styles/views/about/about-view.css")
public class PermissionDenied extends Div {

	public PermissionDenied() {
		init();
	}

	public PermissionDenied(Component... components) {
		super(components);
		init();
	}

	private void init() {
		this.addComponentAsFirst(new H2("Permission Denied"));
	}

}
