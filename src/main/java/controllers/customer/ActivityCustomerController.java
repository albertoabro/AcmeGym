package controllers.customer;


import domain.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActivityService;
import services.CustomerService;

import java.util.Collection;


@Controller
@RequestMapping("/activity/customer")
public class ActivityCustomerController {


    //Services
    //******************************************************************************************************************

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CustomerService customerService;




    //Constructor
    //******************************************************************************************************************

    public ActivityCustomerController() { super(); }




    //Methods
    //******************************************************************************************************************

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {

        Collection<Activity> activities;
        ModelAndView view = new ModelAndView("/activities/list");

        activities = this.activityService.findAllActive();
        view.addObject("requestURI", "activity/customer/list.do");
        view.addObject("activities", activities);

        return view;
    }





}
