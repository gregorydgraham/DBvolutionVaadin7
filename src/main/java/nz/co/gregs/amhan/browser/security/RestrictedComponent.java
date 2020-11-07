/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.security;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

/**
 *
 * @author gregorygraham
 */
public interface RestrictedComponent extends BeforeEnterObserver {

	@Override
	public default void beforeEnter(BeforeEnterEvent event) {
		if (checkForIdentity()) {
			if (checkForPermission()) {
				authorisedToEnter(event);
			} else {
				notAuthorisedToEnter(event);
			}
		} else {
			notLoggedIn(event);
		}
	}

	default public boolean checkForIdentity(){return true;}

	default public boolean checkForPermission(){return true;}

	default public void authorisedToEnter(BeforeEnterEvent event){}

	default public void notAuthorisedToEnter(BeforeEnterEvent event){}

	default public void notLoggedIn(BeforeEnterEvent event){}
}
