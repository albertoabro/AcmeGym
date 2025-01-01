package controllers.admin;

import controllers.AbstractController;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ManagerService;

import java.util.Collection;

/**
 * This class is for the administrator to control Managers (Ban/unban)
 */

@Controller
@RequestMapping("/manager")
public class ManagerAdministratorController extends AbstractController {


    //Services
    //------------------------------------------------------------------------------------------------------------------
    @Autowired
    private ManagerService managerService;


    public ManagerAdministratorController() { super(); }


    @RequestMapping(value = "/list")
    public ModelAndView list() {

        Collection<Manager> managers = this.managerService.findAll();
        ModelAndView view = new ModelAndView("manager/list");;

        view.addObject("requestURI", "manager/administrator/list.do");
        view.addObject("managers", managers);

        return view;
    }



}
