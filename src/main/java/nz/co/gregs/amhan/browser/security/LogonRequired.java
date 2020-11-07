/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.security;

import nz.co.gregs.amhan.browser.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

/**
 *
 * @author gregorygraham
 */
@Route(value = "logonrequired", layout = MainView.class)
@PageTitle("Please Logon")
@CssImport("./styles/views/about/about-view.css")
public class LogonRequired extends Div {

	public LogonRequired() {
		init();
	}

	public LogonRequired(Component... components) {
		super(components);
		init();
	}

	private void init() {
		this.addComponentAsFirst(new Paragraph("The feature you tried to access requires that you be an authenitcated user"));
		this.addComponentAsFirst(new Paragraph(", please sign up or log on to proceed."));
		this.addComponentAsFirst(new H2("Logon Required"));
	}

}
