package converters;

import domain.CensoredWords;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class CensoredWordToStringConverter implements Converter<CensoredWords, String> {
    @Override
    public String convert(final CensoredWords source) {
        String result;
        if(source==null)
            result = null;
        else
            result = source.getCensored();
        return result;
    }
}
