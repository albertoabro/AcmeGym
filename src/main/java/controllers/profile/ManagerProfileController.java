
package controllers.profile;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Admin;
import domain.Annotation;
import domain.Gym;
import domain.Manager;
import domain.Person;
import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import services.AdminService;
import services.AnnotationService;
import services.GymService;
import services.ManagerService;
import services.PersonService;

@Controller
@RequestMapping("/profile/manager")
public class ManagerProfileController extends AbstractController {

	@Autowired
	PersonService personService;
	@Autowired
	UserAccountService userAccountService;
	@Autowired
	ManagerService managerService;
	@Autowired
	AnnotationService annotationService;
	@Autowired
	GymService gymService;
	@Autowired
	AdminService adminService;


	@RequestMapping(value = "/list")
	public ModelAndView view(@RequestParam final String username) {
		ModelAndView modelAndView;
		final UserAccount account = this.userAccountService.findByUsername(username);
		final Person person = this.personService.findByIdAccount(account.getId());
		modelAndView = new ModelAndView("profile/manager/list");
		modelAndView.addObject("requestUri", "profile/manager/list.do");
		modelAndView.addObject("person", person);

		return modelAndView;
	}

	@RequestMapping(value = "/create")
	public ModelAndView create() {
		ModelAndView modelAndView;
		final Manager manager = this.managerService.create();

		final String path = "manager";
		final boolean create = true;
		modelAndView = this.createEditModelAndView(manager, path, create);
		return modelAndView;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView editManager(@RequestParam final int idPerson) {
		ModelAndView modelAndView;
		final Manager person = this.managerService.findOne(idPerson);
		final boolean create = false;
		final String path = "manager";
		modelAndView = this.createEditModelAndView(person, path, create);
		return modelAndView;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView createManager(@Valid final Manager person, final BindingResult binding) {
		ModelAndView modelAndView;
		UserAccount account;
		final String path = "manager";
		final boolean create = false;
		if (binding.hasErrors())
			modelAndView = this.createEditModelAndView(person, path, binding.toString(), create);
		else
			try {

				final String pass = personService.toMD5(person.getUserAccount().getPassword());
				person.getUserAccount().setPassword(pass);

				this.managerService.save(person);
				account = person.getUserAccount();
				modelAndView = this.view(account.getUsername());
			} catch (final Throwable oops) {
				modelAndView = this.createEditModelAndView(person, "profile.commit.error", create);
			}

		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveManager(@Valid final Manager person, final BindingResult binding) {
		ModelAndView modelAndView;
		UserAccount account;
		final String path = "manager";
		final boolean create = false;
		if (binding.hasErrors())
			modelAndView = this.createEditModelAndView(person, path, binding.toString(), create);
		else
			try {
				this.managerService.save(person);
				account = person.getUserAccount();
				modelAndView = this.view(account.getUsername());
			} catch (final Throwable oops) {
				modelAndView = this.createEditModelAndView(person, "profile.commit.error", create);
			}

		return modelAndView;
	}

	protected ModelAndView createEditModelAndView(final Person person, final String path, final boolean create) {
		final ModelAndView modelAndView = this.createEditModelAndView(person, path, null, create);
		return modelAndView;
	}

	protected ModelAndView createEditModelAndView(final Person person, final String path, final String message, final boolean create) {
		ModelAndView modelAndView;
		if (!create)
			modelAndView = new ModelAndView("profile/" + path + "/edit");
		else
			modelAndView = new ModelAndView("manager/create");
		UserAccount account;
		Collection<Annotation> annotations;
		account = person.getUserAccount();
		annotations = person.getPersonAnnotations();
		final Authority authority = new Authority();
		authority.setAuthority(Authority.MANAGER);
		final Collection<Authority> authorities = new HashSet<>();
		authorities.add(authority);
		final Collection<Gym> gyms = this.gymService.findAll();
		final Collection<Admin> admins = this.adminService.findAll();

		modelAndView.addObject("person", person);
		modelAndView.addObject("message", message);
		modelAndView.addObject("userAccount", account);
		modelAndView.addObject("personAnnotations", annotations);
		modelAndView.addObject("gyms", gyms);
		modelAndView.addObject("admins", admins);
		modelAndView.addObject("path", path);
		modelAndView.addObject("authorities", authorities);
		modelAndView.addObject("createManager", create);
		return modelAndView;
	}
}
