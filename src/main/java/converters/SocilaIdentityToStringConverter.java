package converters;

import domain.SocialIdentity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class SocilaIdentityToStringConverter implements Converter<SocialIdentity, String> {
    @Override
    public String convert(final SocialIdentity source) {
        String result;
        if(source==null)
            result = null;
        else
            result = String.valueOf(source.getId());
        return result;
    }
}
