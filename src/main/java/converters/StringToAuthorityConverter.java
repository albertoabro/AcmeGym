package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import security.Authority;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToAuthorityConverter implements Converter<String, Authority> {

    @Override
    public Authority convert(final String text) {
        Authority result;

        try {
            result = new Authority();
            result.setAuthority(text);
        } catch (final Throwable oops) {
            throw new IllegalArgumentException(oops);
        }

        return result;
    }
}
