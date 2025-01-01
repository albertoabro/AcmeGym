package controllers.training;

import controllers.AbstractController;
import domain.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.TrainerService;
import services.TrainingService;

import java.util.Collection;

@Controller
@RequestMapping("/training")
public class TrainingController extends AbstractController {
    @Autowired
    TrainingService trainingService;

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView;
        Collection<Training> trainings = trainingService.findAll();

        modelAndView = new ModelAndView("training/list");
        modelAndView.addObject("requestUri","training/list.do");
        modelAndView.addObject("trainings",trainings);

        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam final String word){
        ModelAndView modelAndView;
        Collection<Training> trainings = trainingService.findByWord(word);
        modelAndView = new ModelAndView("training/list");
        modelAndView.addObject("requestUri","training/list.do");
        modelAndView.addObject("trainings",trainings);

        return modelAndView;
    }
}
