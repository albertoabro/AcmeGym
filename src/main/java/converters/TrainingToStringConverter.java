package converters;

import domain.Training;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class TrainingToStringConverter implements Converter<Training,String> {
    @Override
    public String convert(final Training source) {
        String result;
        if(source==null)
            result = null;
        else
            result = String.valueOf(source.getId());
        return result;
    }
}
