/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.views.errors;

import nz.co.gregs.amhan.browser.views.main.MainView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import nz.co.gregs.dbvolution.exceptions.DBRuntimeException;

/**
 *
 * @author gregorygraham
 */
@ParentLayout(MainView.class)
@PageTitle("Database Error")
public class DBvolutionErrorPage extends Div implements HasErrorParameter<DBRuntimeException>{

	@Override
	public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<DBRuntimeException> parameter) {
		final DBRuntimeException exc = parameter.getException();
		exc.fillInStackTrace();
		this.add(new Paragraph(exc.getLocalizedMessage()));
		this.add(new Paragraph(exc.getMessage()));
		StackTraceElement[] stackTrace = exc.getStackTrace();
		for (StackTraceElement trace : stackTrace) {
			this.add(new Paragraph(trace.toString()));
		}
		return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}
	
}
