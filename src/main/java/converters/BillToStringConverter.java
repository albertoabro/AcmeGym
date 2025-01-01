package converters;

import domain.Bill;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class BillToStringConverter implements Converter<Bill, String> {
    @Override
    public String convert(final Bill source) {
        String result;
        if(source==null)
            result = null;
        else
            result = String.valueOf(source.getId());
        return result;
    }
}
