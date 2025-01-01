
package controllers.admin;

import java.util.Collection;

import controllers.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Admin;
import domain.Manager;
import services.AdminService;
import services.ManagerService;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

	@Autowired
	AdminService	adminService;
	@Autowired
	ManagerService	managerService;


	@RequestMapping("/manager/list")
	public ModelAndView list() {
		ModelAndView modelAndView;
		final Admin admin = this.adminService.findByPrincipal();
		final Collection<Manager> managers = this.managerService.findByAdminId(admin.getId());

		modelAndView = new ModelAndView("admin/manager/list");
		modelAndView.addObject("requestUri", "admin/manager/list.do");
		modelAndView.addObject("managers", managers);

		return modelAndView;
	}

	@RequestMapping(value = "/manager/ban", method = RequestMethod.POST)
	public ModelAndView ban(@RequestParam final int idManager) {
		ModelAndView modelAndView;

		final Manager manager = this.managerService.findOne(idManager);
		if (manager.isBanned()) {
			manager.setBanned(false);
			manager.getUserAccount().setAccountNonLocked(true);
		} else {
			manager.setBanned(true);
			manager.getUserAccount().setAccountNonLocked(false);
		}
		this.managerService.save(manager);
		modelAndView = this.list();

		return modelAndView;
	}
}
