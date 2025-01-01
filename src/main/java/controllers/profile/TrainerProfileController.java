
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
import domain.Person;
import domain.Trainer;
import security.UserAccount;
import security.UserAccountService;
import services.AnnotationService;
import services.PersonService;
import services.TrainerService;

@Controller
@RequestMapping("/profile/trainer")
public class TrainerProfileController extends AbstractController {

	@Autowired
	PersonService		personService;
	@Autowired
	UserAccountService	userAccountService;
	@Autowired
	TrainerService		trainerService;
	@Autowired
	AnnotationService	annotationService;


	@RequestMapping(value = "/list")
	public ModelAndView view(@RequestParam final String username) {
		ModelAndView modelAndView;
		final UserAccount account = this.userAccountService.findByUsername(username);
		final Person person = this.personService.findByIdAccount(account.getId());
		modelAndView = new ModelAndView("profile/trainer/list");
		modelAndView.addObject("requestUri", "profile/trainer/list.do");
		modelAndView.addObject("person", person);

		return modelAndView;
	}
	@RequestMapping(value = "/edit")
	public ModelAndView editTrainer(@RequestParam final int idPerson) {
		ModelAndView modelAndView;
		final Trainer person = this.trainerService.findOne(idPerson);

		final String path = "trainer";
		modelAndView = this.createEditModelAndView(person, path);
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveTrainer(@Valid final Trainer person, final BindingResult binding) {
		ModelAndView modelAndView;
		UserAccount account;
		final String path = "trainer";
		if (binding.hasErrors())
			modelAndView = this.createEditModelAndView(person, path, binding.toString());
		else
			try {
				this.trainerService.save(person);
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

		modelAndView.addObject("person", person);
		modelAndView.addObject("message", message);
		modelAndView.addObject("userAccount", account);
		modelAndView.addObject("personAnnotations", annotations);
		modelAndView.addObject("path", path);
		return modelAndView;
	}
}
