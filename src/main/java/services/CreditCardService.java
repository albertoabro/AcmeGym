package services;


import domain.CreditCard;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CreditCardRepository;

import java.util.Collection;

@Service
@Transactional
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CustomerService customerService;

    public Collection<CreditCard> findByPrincipal(){
        Collection<CreditCard> creditCards;
        Customer customer;
        customer = customerService.findByPrincipal();

        creditCards = customer.getCustomerCreditCards();
        return creditCards;
    }

    public CreditCard createCreditCard(){
        CreditCard creditCard;
        Customer customer;

        creditCard = new CreditCard();
        customer = customerService.findByPrincipal();
        creditCard.setCreditCardCustomer(customer);

        return creditCard;
    }

    public CreditCard save(CreditCard creditCard){
        Assert.notNull(creditCard);

        CreditCard result = creditCardRepository.save(creditCard);

        return result;
    }

    public void delete(CreditCard creditCard){
        Assert.notNull(creditCard);
        Assert.isTrue(creditCard.getId() != 0);

        creditCardRepository.delete(creditCard);
    }
}
