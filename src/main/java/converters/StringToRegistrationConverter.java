package converters;

import domain.Activity;
import domain.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.RegistrationRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToRegistrationConverter implements Converter<String, Registration> {
    @Autowired
    RegistrationRepository registrationRepository;

    @Override
    public Registration convert(final String source) {
        Registration resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.registrationRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
