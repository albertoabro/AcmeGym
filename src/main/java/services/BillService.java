package services;


import domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.BillRepository;

import java.util.Collection;

@Service
@Transactional
public class BillService {
    @Autowired
    BillRepository billRepository;

    public BillService() {
        super();
    }

    public Collection<Bill> findAll(){
        Collection<Bill>bills = billRepository.findAll();
        Assert.notNull(bills);
        return bills;
    }

    public Bill findOne(int billId){
        Assert.notNull(billId);
        Bill bill = billRepository.findOne(billId);
        Assert.notNull(bill);
        return bill;
    }

    public Bill create(){
        return new Bill();
    }

    public Bill save(Bill bill){
        Assert.notNull(bill);
        return billRepository.save(bill);
    }

    public void delete (Bill bill){
        Assert.notNull(bill);
        Assert.isTrue(bill.getId()!=0);
        bill.setDeleted(true);
    }
}
