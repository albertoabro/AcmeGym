package services;

import domain.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.RegistrationRepository;

import java.util.Collection;

@Service
@Transactional
public class RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    GymService gymService;
    @Autowired
    BillService billService;

    public Collection<Registration> findAll(){
        Collection<Registration> registrations;
        registrations = registrationRepository.findAll();
        Assert.notNull(registrations);

        return registrations;
    }

    public Registration findOne(int registrationId){
        Registration registration;
        registration = registrationRepository.findOne(registrationId);
        Assert.notNull(registration);

        return registration;
    }

    public Registration save(Registration registration) {
        Assert.notNull(registration);

        Registration result;

        result = registrationRepository.save(registration);

        return result;
    }

    public void delete(Registration registration) {
        Assert.notNull(registration);
        Assert.isTrue(registration.getId() != 0);

        registrationRepository.delete(registration);
    }

}
