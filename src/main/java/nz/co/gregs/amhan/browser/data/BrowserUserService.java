/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.co.gregs.amhan.browser.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrowserUserService {

	private Database repository;

	public BrowserUserService(@Autowired Database repository) {
		this.repository = repository;
	}

	protected Database getRepository() {
		return repository;
	}

}
