package converters;

import domain.Activity;
import domain.Speciality;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToSpecialityConverter implements Converter<String, Speciality> {

    @Override
    public Speciality convert(final String source) {
        Speciality resul;
        try{
            resul = new Speciality();
            resul.setSpeciality(source);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
