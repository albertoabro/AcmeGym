package controllers.trainer;

import controllers.AbstractController;
import domain.Trainer;
import domain.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.TrainerService;

import java.util.Collection;

@Controller
@RequestMapping("/trainer")
public class TrainerController extends AbstractController {
    @Autowired
    TrainerService trainerService;

    @RequestMapping(value = "/list")
    public ModelAndView list(){
        Collection<Trainer> trainers;
        ModelAndView modelAndView;
        trainers = trainerService.findAll();

        modelAndView = new ModelAndView("trainer/list");
        modelAndView.addObject("requestUri","trainer/list.do");
        modelAndView.addObject("trainers",trainers);

        return modelAndView;
    }

    @RequestMapping(value = "/listByIdActivity")
    public ModelAndView listTrainerFromActivity(@RequestParam final int idActivity){
        Collection<Trainer> trainers;
        ModelAndView modelAndView;
        trainers = trainerService.findTrainerByIdActivity(idActivity);

        modelAndView = new ModelAndView("trainer/listByIdActivity");
        modelAndView.addObject("requestUri","trainer/listByIdActivity.do");
        modelAndView.addObject("trainers",trainers);

        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam final String word){
        ModelAndView modelAndView;
        Collection<Trainer> trainers = trainerService.findByWord(word);
        modelAndView = new ModelAndView("trainer/list");
        modelAndView.addObject("requestUri","trainer/list.do");
        modelAndView.addObject("trainers",trainers);

        return modelAndView;
    }
}
