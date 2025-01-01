package controllers.activity;

import controllers.AbstractController;
import domain.Activity;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActivityService;
import services.ManagerService;

import java.util.Collection;

@Controller
@RequestMapping("/activity/manager")
public class ManagerActivityController extends AbstractController {
    @Autowired
    ActivityService activityService;
    @Autowired
    ManagerService managerService;

    @RequestMapping(value = "list")
    public ModelAndView listActivityMangement(){
        ModelAndView modelAndView;
        Manager manager = managerService.findByPrincipal();
        Collection<Activity> activities = activityService.findByIdManager(manager.getId());
        modelAndView=new ModelAndView("activity/list");
        modelAndView.addObject("requestUri","activity/manager/list");
        modelAndView.addObject("activities", activities);
        modelAndView.addObject("notPermission", true);
        return modelAndView;
    }
    @RequestMapping(value = "/listByGym")
    public ModelAndView listActivityFromGym(@RequestParam final int idGym){
        Collection<Activity>   activities;
        ModelAndView modelAndView;
        activities = activityService.findByIdGym(idGym);
        modelAndView = new ModelAndView("activity/list");
        modelAndView.addObject("requestUri","activity/manager/listByGym.do");
        modelAndView.addObject("activities",activities);

        return modelAndView;
    }
}
