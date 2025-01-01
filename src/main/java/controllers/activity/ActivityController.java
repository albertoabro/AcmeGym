
package controllers.activity;

import java.util.Collection;
import java.util.HashSet;

import controllers.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Activity;
import services.ActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController extends AbstractController {

	@Autowired
	ActivityService activityService;


	@RequestMapping(value = "/listByGym")
	public ModelAndView listActivityFromGym(@RequestParam final int idGym) {
		Collection<Activity> activities;
		ModelAndView modelAndView;
		activities = this.activityService.findActiveByIdGym(idGym);
		modelAndView = new ModelAndView("activity/list");
		modelAndView.addObject("requestUri", "activity/listByGym.do");
		modelAndView.addObject("activities", activities);

		return modelAndView;
	}

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView modelAndView;
		Collection<Activity> activities;
		activities = this.activityService.findAllActive();
		modelAndView = new ModelAndView("activity/list");
		modelAndView.addObject("requestUri", "activity/list.do");
		modelAndView.addObject("activities", activities);

		return modelAndView;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam final String word, @RequestParam final String time, @RequestParam final String day){
		ModelAndView modelAndView;
		Collection<Activity> activities = activityService.findByFilter(word, day, time);

		modelAndView = new ModelAndView("activity/list");
		modelAndView.addObject("requestUri", "activity/list.do");
		modelAndView.addObject("activities", activities);
		return modelAndView;
	}
}
