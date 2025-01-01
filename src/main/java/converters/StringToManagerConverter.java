package converters;

import domain.Activity;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.ManagerRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToManagerConverter implements Converter<String, Manager> {
    @Autowired
    ManagerRepository managerRepository;

    @Override
    public Manager convert(final String source) {
        Manager resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.managerRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
