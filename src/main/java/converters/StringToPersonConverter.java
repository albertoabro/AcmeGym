package converters;

import domain.Activity;
import domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.PersonRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToPersonConverter implements Converter<String, Person> {
    @Autowired
    PersonRepository personRepository;
    @Override
    public Person convert(final String source) {
        Person resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.personRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
