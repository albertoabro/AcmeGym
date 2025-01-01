package converters;

import domain.Activity;
import domain.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.TrainingRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToTrainingConverter implements Converter<String, Training> {
    @Autowired
    TrainingRepository trainingRepository;
    @Override
    public Training convert(final String source) {
        Training resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.trainingRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
