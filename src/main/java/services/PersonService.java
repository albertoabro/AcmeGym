package services;

import domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.PersonRepository;
import security.UserAccount;
import security.UserAccountService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserAccountService userAccountService;

    public PersonService() {
        super();
    }

    public Collection<Person> findAll() {
        Collection<Person> result;

        result = personRepository.findAll();
        Assert.notNull(result);

        return result;
    }

    public Person findOne(int personId) {
        Assert.isTrue(personId != 0);

        Person result;

        result = personRepository.findOne(personId);
        Assert.notNull(result);

        return result;
    }

    public Person save(Person person) {
        Assert.notNull(person);

        Person result;

        result = personRepository.save(person);

        return result;
    }

    public void delete(Person person) {
        Assert.notNull(person);
        Assert.isTrue(person.getId() != 0);
        Assert.isTrue(personRepository.exists(person.getId()));

        personRepository.delete(person);
    }


    public UserAccount findUserAccount(Person person) {
        Assert.notNull(person);

        UserAccount result;

        result = userAccountService.findByPerson(person);

        return result;
    }

    public Person findByIdAccount(int idAccount) {
        Assert.notNull(idAccount);

        Person result = personRepository.findByIdAccount(idAccount);

        return result;
    }

    public String toMD5(final String pass) {
        final byte[] msg = pass.getBytes();

        byte[] hash = null;
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            hash = md.digest(msg);
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        final StringBuilder sb = new StringBuilder();
        for (final byte b : hash)
            sb.append(String.format("%02x", b));

        final String password = sb.toString();

        return password;
    }
}
