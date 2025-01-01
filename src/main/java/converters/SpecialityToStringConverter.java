package converters;

import domain.Speciality;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class SpecialityToStringConverter implements Converter<Speciality, String> {
    @Override
    public String convert(final Speciality source) {
        String result;
        if(source==null)
            result = null;
        else
            result = source.getSpeciality();
        return result;
    }
}
