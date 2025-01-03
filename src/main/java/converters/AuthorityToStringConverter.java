package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import security.Authority;

import javax.transaction.Transactional;

@Component
@Transactional
public class AuthorityToStringConverter implements Converter<Authority, String> {
    @Override
    public String convert(final Authority authority) {
        String result;

        if (authority == null)
            result = null;
        else
            result = authority.getAuthority();

        return result;
    }
}
