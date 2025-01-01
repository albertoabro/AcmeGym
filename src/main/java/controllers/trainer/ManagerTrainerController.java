
package controllers.trainer;

import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import controllers.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Activity;
import domain.Gym;
import domain.Manager;
import domain.Trainer;
import security.Authority;
import security.UserAccount;
import services.*;

@Controller
@RequestMapping("/trainer/manager")
public class ManagerTrainerController extends AbstractController {

	@Autowired
	TrainerService	trainerService;
	@Autowired
	ManagerService	managerService;
	@Autowired
	GymService		gymService;
	@Autowired
	ActivityService	activityService;
	@Autowired
	PersonService personService;


	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView modelAndView;
		Collection<Trainer> trainers;

		final Manager manager = this.managerService.findByPrincipal();
		trainers = this.trainerService.findByIdManager(manager.getId());

		modelAndView = new ModelAndView("trainer/list");
		modelAndView.addObject("requestUri", "trainer/manager/list.do");
		modelAndView.addObject("trainers", trainers);
		modelAndView.addObject("permission", true);

		return modelAndView;
	}

	@RequestMapping(value = "/create")
	public ModelAndView create() {
		ModelAndView modelAndView;
		final Trainer trainer = this.trainerService.create();

		modelAndView = this.createEditModelAndView(trainer);
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Trainer trainer, final BindingResult binding) {
		ModelAndView modelAndView;

		if (binding.hasErrors())
			modelAndView = this.createEditModelAndView(trainer, binding.toString());
		else
			try {

				final String pass = personService.toMD5(trainer.getUserAccount().getPassword());
				trainer.getUserAccount().setPassword(pass);

				this.trainerService.save(trainer);
				modelAndView = this.list();
			} catch (final Throwable oops) {
				modelAndView = this.createEditModelAndView(trainer, "trainer.commit.error");
			}

		return modelAndView;
	}

	private ModelAndView createEditModelAndView(final Trainer trainer) {
		final ModelAndView modelAndView = this.createEditModelAndView(trainer, null);
		return modelAndView;
	}

	private ModelAndView createEditModelAndView(final Trainer trainer, final String message) {
		ModelAndView modelAndView;
		final UserAccount account = trainer.getUserAccount();
		Collection<Gym> gyms;
		Collection<Activity> activities;
		final Authority authority = new Authority();
		authority.setAuthority(Authority.TRAINER);
		final Collection<Authority> authorities = new HashSet<>();
		authorities.add(authority);

		final Manager manager = this.managerService.findByPrincipal();

		gyms = this.gymService.findByIdManager(manager.getId());
		activities = this.activityService.findByIdManager(manager.getId());

		modelAndView = new ModelAndView("trainer/edit");
		modelAndView.addObject("trainer", trainer);
		modelAndView.addObject("gyms", gyms);
		modelAndView.addObject("activities", activities);
		modelAndView.addObject("authority", authorities);
		modelAndView.addObject("userAccount", account);
		modelAndView.addObject("message", message);

		return modelAndView;
	}
}
