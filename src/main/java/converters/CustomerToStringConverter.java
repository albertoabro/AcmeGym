package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import domain.Customer;
import javax.transaction.Transactional;

@Component
@Transactional
public class CustomerToStringConverter implements Converter<Customer,String> {

    @Override
    public String convert(final Customer source) {
        String result;
        if(source==null)
            result = null;
        else
            result = String.valueOf(source.getId());
        return result;
    }
}
