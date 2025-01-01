package converters;

import domain.Activity;
import domain.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.TrainerRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToTrainerConverter implements Converter<String, Trainer> {
    @Autowired
    TrainerRepository trainerRepository;

    @Override
    public Trainer convert(final String source) {
        Trainer resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.trainerRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
