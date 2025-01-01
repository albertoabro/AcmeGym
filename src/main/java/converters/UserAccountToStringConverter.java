package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import security.UserAccount;

import javax.transaction.Transactional;

@Transactional
@Component
public class UserAccountToStringConverter implements Converter<UserAccount, String> {
    @Override
    public String convert(final UserAccount source){
        String result;
        if(source==null)
            result=null;
        else
            result = source.toString();

        return result;
    }
}
