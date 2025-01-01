package converters;

import domain.Activity;
import domain.Gym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.GymRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToGymConverter implements Converter<String, Gym> {
    @Autowired
    GymRepository gymRepository;

    @Override
    public Gym convert(final String source) {
        Gym resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.gymRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
