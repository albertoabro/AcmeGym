package converters;

import domain.Activity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;

@Component
@Transactional
public class ActivityToStringConverter implements Converter<Activity, String>
{
    @Override
    public String convert(final Activity source) {
        String result;
        if(source==null)
            result = null;
        else
            result = String.valueOf(source.getId());
        return result;
    }
}
