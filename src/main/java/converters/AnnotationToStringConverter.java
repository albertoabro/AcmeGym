package converters;

import domain.Annotation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class AnnotationToStringConverter implements Converter<Annotation, String> {
    @Override
    public String convert(final Annotation source) {
        String result;
        if(source==null)
            result = null;
        else
            result = String.valueOf(source.getId());
        return result;
    }
}
