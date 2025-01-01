package converters;

import domain.Activity;
import domain.CensoredWords;
import domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import repositories.CreditCardRepository;

import javax.transaction.Transactional;

@Component
@Transactional
public class StringToCreditCardConverter implements Converter<String, CreditCard> {
    @Autowired
    CreditCardRepository creditCardRepository;

    @Override
    public CreditCard convert(final String source) {
        CreditCard resul;
        int id;
        try{
            id = Integer.valueOf(source);
            resul = this.creditCardRepository.findOne(id);
        }catch (final Throwable oops){
            throw new IllegalArgumentException(oops);
        }
        return resul;
    }
}
