package converters;

import domain.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.ActivityRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToActivityConverter implements Converter<String, Activity> {
    @Autowired
    ActivityRepository activityRepository;

    @Override
    public Activity convert(final String source) {
        Activity resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.activityRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
