package converters;

import domain.Activity;
import domain.Annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.AnnotationRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToAnnotationConverter implements Converter<String, Annotation> {
    @Autowired
    AnnotationRepository annotationRepository;

    @Override
    public Annotation convert(final String source) {
        Annotation result;
        int id;
        try{
            id = Integer.valueOf(source);
            result = this.annotationRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
