package converters;

import domain.Activity;
import domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.AdminRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToAdminConverter implements Converter<String, Admin> {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin convert(final String source) {
        Admin result;
        int id;
        try{
            id = Integer.valueOf(source);
            result = this.adminRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
