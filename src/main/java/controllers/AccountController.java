
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class AccountController {

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		final ModelAndView modelAndView = new ModelAndView("account/list");
		modelAndView.addObject("requestUri", "account/list.do");
		return modelAndView;
	}
}
