
package controllers.training;

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

import domain.Annotation;
import domain.Gym;
import domain.Manager;
import domain.Step;
import domain.Training;
import services.GymService;
import services.ManagerService;
import services.StepService;
import services.TrainingService;

@Controller
@RequestMapping("training/manager")
public class ManagerTrainingController {

	@Autowired
	TrainingService trainingService;
	@Autowired
	ManagerService managerService;
	@Autowired
	GymService gymService;
	@Autowired
	StepService stepService;


	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView modelAndView;
		final Manager manager = this.managerService.findByPrincipal();
		final Collection<Training> trainings = this.trainingService.findByIdManager(manager.getId());

		modelAndView = new ModelAndView("training/list");
		modelAndView.addObject("requestUri", "training/manager/list.do");
		modelAndView.addObject("trainings", trainings);

		return modelAndView;
	}

	@RequestMapping("/create")
	public ModelAndView create() {
		ModelAndView modelAndView;
		final Training training = this.trainingService.create();
		final boolean create = true;
		modelAndView = this.createEditModelAndView(training, create);
		return modelAndView;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam final int idTraining) {
		final Training training = this.trainingService.findOne(idTraining);
		final boolean create = false;
		return this.createEditModelAndView(training, create);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Training training, final BindingResult result) {
		ModelAndView modelAndView;

		if (result.hasErrors())
			modelAndView = this.createEditModelAndView(training, result.toString(), false);
		else
			try {
				for(Step s : training.getTrainingSteps())
					System.out.println("Step:" + s.getTitle());
				this.trainingService.save(training);
				modelAndView = this.list();

			} catch (final Throwable oops) {
				modelAndView = this.createEditModelAndView(training, "training.commit.error", false);
			}

		return modelAndView;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Training training, final BindingResult result) {
		ModelAndView modelAndView;

		if (result.hasErrors())
			modelAndView = this.createEditModelAndView(training, result.toString(), false);
		else
			try {
				training.setTrainingSteps(new HashSet<>());
				this.trainingService.delete(training);
				modelAndView = this.list();

			} catch (final Throwable oops) {
				modelAndView = this.createEditModelAndView(training, "training.commit.error", false);
			}

		return modelAndView;
	}

	private ModelAndView createEditModelAndView(final Training training, final boolean create) {
		final ModelAndView modelAndView = this.createEditModelAndView(training, null, create);
		return modelAndView;
	}

	private ModelAndView createEditModelAndView(final Training training, final String message, final boolean create) {
		ModelAndView modelAndView;
		final Manager manager = this.managerService.findByPrincipal();
		final Collection<Gym> gyms = this.gymService.findByIdManager(manager.getId());
		final Collection<Step> steps = this.stepService.findAll();
		final Collection<Annotation> annotations = new HashSet<>();

		modelAndView = new ModelAndView("training/manager/edit");
		modelAndView.addObject("training", training);
		modelAndView.addObject("gyms", gyms);
		modelAndView.addObject("steps", steps);
		modelAndView.addObject("trainingAnnotations", annotations);
		modelAndView.addObject("create", create);
		modelAndView.addObject("message", message);

		return modelAndView;
	}
}
