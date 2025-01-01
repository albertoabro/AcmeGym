package converters;

import domain.Registration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class RegistrationToStringConverter implements Converter<Registration, String> {
    @Override
    public String convert(final Registration source) {
        String result;
        if(source==null)
            result = null;
        else
            result = String.valueOf(source.getId());
        return result;
    }
}
