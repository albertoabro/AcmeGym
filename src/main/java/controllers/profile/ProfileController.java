/*
 * ProfileController.java
 *
 * Copyright (C) 2018 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.profile;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import security.UserAccountService;
import services.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	@Autowired
	PersonService personService;
	@Autowired
	UserAccountService userAccountService;


	@RequestMapping(value = "/list")
	public ModelAndView view(@RequestParam final String username) {
		ModelAndView modelAndView;
		UserAccount account = this.userAccountService.findByUsername(username);
		Person person = this.personService.findByIdAccount(account.getId());
		modelAndView = new ModelAndView("profile/list");
		modelAndView.addObject("requestUri", "profile/list.do");
		modelAndView.addObject("person", person);

		return modelAndView;
	}

}
