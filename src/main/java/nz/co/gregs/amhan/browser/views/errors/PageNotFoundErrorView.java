/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.views.errors;

import nz.co.gregs.amhan.browser.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.*;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.ConcurrencyFailureException;

/**
 *
 * @author gregorygraham
 */
@ParentLayout(MainView.class)
@PageTitle("No Such Destination")
public class PageNotFoundErrorView extends Div implements HasErrorParameter<NotFoundException> {

	@Override
	public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
		this.add(new Paragraph("Could not find a destination for route"));
		this.add(new Paragraph(parameter.getException().getLocalizedMessage()));
		return HttpServletResponse.SC_NOT_FOUND;
	}

}
