
package controllers.profile;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Annotation;
import domain.Customer;
import domain.Person;
import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import services.AnnotationService;
import services.CustomerService;
import services.PersonService;

@Controller
@RequestMapping("profile/customer")
public class CustomerProfileController extends AbstractController {

	@Autowired
	PersonService personService;
	@Autowired
	UserAccountService userAccountService;
	@Autowired
	CustomerService customerService;
	@Autowired
	AnnotationService annotationService;


	@RequestMapping(value = "/list")
	public ModelAndView view(@RequestParam final String username) {
		ModelAndView modelAndView;
		final UserAccount account = this.userAccountService.findByUsername(username);
		final Person person = this.personService.findByIdAccount(account.getId());
		modelAndView = new ModelAndView("profile/list");
		modelAndView.addObject("requestUri", "profile/list.do");
		modelAndView.addObject("person", person);

		return modelAndView;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView editCustomer(@RequestParam final int idPerson) {
		ModelAndView modelAndView;
		final Customer person = this.customerService.findOne(idPerson);

		final String path = "customer";
		modelAndView = this.createEditModelAndView(person, path);
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCustomer(@Valid final Customer person, final BindingResult binding) {
		ModelAndView modelAndView;
		UserAccount account;
		final String path = "customer";
		if (binding.hasErrors())
			modelAndView = this.createEditModelAndView(person, path, binding.toString());
		else
			try {
				this.customerService.save(person);
				account = person.getUserAccount();
				modelAndView = this.view(account.getUsername());
			} catch (final Throwable oops) {
				modelAndView = this.createEditModelAndView(person, "profile.commit.error");
			}

		return modelAndView;
	}

	protected ModelAndView createEditModelAndView(final Person person, final String path) {
		final ModelAndView modelAndView = this.createEditModelAndView(person, path, null);
		return modelAndView;
	}

	protected ModelAndView createEditModelAndView(final Person person, final String path, final String message) {
		final ModelAndView modelAndView = new ModelAndView("profile/" + path + "/edit");
		UserAccount account;
		Collection<Annotation> annotations;
		account = person.getUserAccount();
		annotations = person.getPersonAnnotations();
		int numAuthority = person.getUserAccount().getAuthorities().size();

		modelAndView.addObject("person", person);
		modelAndView.addObject("message", message);
		modelAndView.addObject("userAccount", account);
		modelAndView.addObject("personAnnotations", annotations);
		modelAndView.addObject("authority", numAuthority);
		modelAndView.addObject("path", path);
		return modelAndView;
	}
}
