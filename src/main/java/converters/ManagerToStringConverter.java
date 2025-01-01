package converters;

import domain.Manager;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class ManagerToStringConverter implements Converter<Manager, String> {
    @Override
    public String convert(final Manager source) {
        String result;
        if(source==null)
            result = null;
        else
            result = String.valueOf(source.getId());
        return result;
    }
}
