package converters;

import domain.Activity;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.CustomerRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToCustomerConverter implements Converter<String,Customer> {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer convert(final String source) {
        Customer resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.customerRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
