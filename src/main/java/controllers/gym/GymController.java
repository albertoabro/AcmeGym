
package controllers.gym;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Gym;
import services.GymService;

@Controller
@RequestMapping("/gym")
public class GymController extends AbstractController {

	@Autowired
	GymService gymService;


	public GymController() {
		super();
	}

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView modelAndView;
		Collection<Gym> gyms;

		gyms = this.gymService.findAll();
		modelAndView = new ModelAndView("gym/list");
		modelAndView.addObject("requestUri", "gym/list.do");
		modelAndView.addObject("gyms", gyms);

		return modelAndView;
	}

	@RequestMapping(value = "/listByIdActivity")
	public ModelAndView listByIdActivity(@RequestParam final int idActivity) {
		ModelAndView modelAndView;
		Collection<Gym> gyms;

		gyms = this.gymService.findByIdActivity(idActivity);
		modelAndView = new ModelAndView("gym/listByIdActivity");
		modelAndView.addObject("requestUri", "gym/listByIdActivity.do");
		modelAndView.addObject("gyms", gyms);

		return modelAndView;
	}
}
