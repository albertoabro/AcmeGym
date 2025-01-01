package converters;

import domain.Activity;
import domain.CensoredWords;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToCensoredWordConverter implements Converter<String, CensoredWords> {
    @Override
    public CensoredWords convert(final String source) {
        CensoredWords result;
        try{
            result = new CensoredWords();
            result.setCensored(source);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
