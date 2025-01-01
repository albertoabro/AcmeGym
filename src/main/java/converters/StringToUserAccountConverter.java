package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import security.UserAccount;
import security.UserAccountRepository;
import security.UserAccountService;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToUserAccountConverter implements Converter<String, UserAccount> {
    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserAccount convert(final String source){
        UserAccount userAccount;
        int id;
        try{
            id=Integer.valueOf(source);
            userAccount = userAccountRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return userAccount;
    }
}
