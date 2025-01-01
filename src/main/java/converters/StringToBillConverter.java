package converters;

import domain.Activity;
import domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.BillRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToBillConverter implements Converter<String, Bill> {
    @Autowired
    BillRepository billRepository;

    @Override
    public Bill convert(final String source) {
        Bill result;
        int id;
        try{
            id = Integer.valueOf(source);
            result = this.billRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return result;
    }
}
