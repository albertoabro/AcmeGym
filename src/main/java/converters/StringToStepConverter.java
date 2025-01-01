package converters;

import domain.Step;
import domain.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.StepRepository;
import repositories.TrainerRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToStepConverter implements Converter<String, Step> {
    @Autowired
    StepRepository stepRepository;

    @Override
    public Step convert(final String source) {
        Step resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.stepRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
