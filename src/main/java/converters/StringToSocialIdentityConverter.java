package converters;

import domain.Activity;
import domain.SocialIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.SocialIdentityRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToSocialIdentityConverter implements Converter<String, SocialIdentity> {
    @Autowired
    SocialIdentityRepository socialIdentityRepository;

    @Override
    public SocialIdentity convert(final String source) {
        SocialIdentity resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.socialIdentityRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
