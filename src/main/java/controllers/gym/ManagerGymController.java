
package controllers.gym;

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
import domain.Activity;
import domain.Admin;
import domain.Gym;
import domain.Manager;
import services.ActivityService;
import services.AdminService;
import services.GymService;
import services.ManagerService;

@Controller
@RequestMapping("/gym/manager")
public class ManagerGymController extends AbstractController {

	@Autowired
	GymService		gymService;
	@Autowired
	ManagerService	managerService;
	@Autowired
	ActivityService	activityService;
	@Autowired
	AdminService	adminService;


	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView modelAndView;
		final Manager manager = this.managerService.findByPrincipal();
		final Collection<Gym> gyms = this.gymService.findByIdManager(manager.getId());

		modelAndView = new ModelAndView("gym/list");
		modelAndView.addObject("requestUri", "gym/manager/list.do");
		modelAndView.addObject("gyms", gyms);

		return modelAndView;
	}
	@RequestMapping(value = "/create")
	public ModelAndView create() {
		ModelAndView modelAndView;
		final Gym gym = this.gymService.create();

		modelAndView = this.createEditModelAndView(gym);
		return modelAndView;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam final int idGym) {
		final Gym gym = this.gymService.findOne(idGym);
		return this.createEditModelAndView(gym);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Gym gym, final BindingResult binding) {
		ModelAndView modelAndView;
		if (binding.hasErrors())
			modelAndView = this.createEditModelAndView(gym, binding.toString());
		else
			try {
				this.gymService.delete(gym.getId());
				for (final Activity activity : gym.getGymActivities())
					this.activityService.cancel(activity.getId());
				modelAndView = this.list();
			} catch (final Throwable oops) {
				modelAndView = this.createEditModelAndView(gym, "gym.commit.error");
			}
		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Gym gym, final BindingResult binding) {
		ModelAndView modelAndView;
		final boolean edit = false;
		if (binding.hasErrors())
			modelAndView = this.createEditModelAndView(gym, binding.toString());
		else
			try {
				this.gymService.save(gym);
				modelAndView = this.list();

			} catch (final Throwable oops) {
				modelAndView = this.createEditModelAndView(gym, "gym.commit.error");
			}

		return modelAndView;
	}
	private ModelAndView createEditModelAndView(final Gym gym) {
		return this.createEditModelAndView(gym, null);
	}

	private ModelAndView createEditModelAndView(final Gym gym, final String message) {
		ModelAndView modelAndView;
		final Collection<Admin> admins = this.adminService.findAll();
		final Collection<Manager> managers = this.managerService.findAllActive();

		modelAndView = new ModelAndView("gym/manager/edit");
		modelAndView.addObject("managers", managers);
		modelAndView.addObject("admins", admins);
		modelAndView.addObject("gym", gym);
		modelAndView.addObject("message", message);

		return modelAndView;
	}

}
