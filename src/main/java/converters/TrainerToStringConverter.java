package converters;

import domain.Trainer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class TrainerToStringConverter implements Converter<Trainer, String> {
    @Override
    public String convert(final Trainer source) {
        String result;
        if(source==null)
            result = null;
        else
            result = String.valueOf(source.getId());
        return result;
    }
}
