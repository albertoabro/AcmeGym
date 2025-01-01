package converters;

import domain.Activity;
import domain.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.CVRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToCvConverter implements Converter<String, CV> {
    @Autowired
    CVRepository cvRepository;

    @Override
    public CV convert(final String source) {
        CV result;
        int id;
        try{
            id = Integer.valueOf(source);
            result = this.cvRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
