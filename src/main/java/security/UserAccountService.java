package security;

import domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    public UserAccountService() { super(); }

    public UserAccount findByUsername(final String username){
        Assert.notNull(username);

        UserAccount result = this.userAccountRepository.findByUsername(username);;

        return result;
    }

    public UserAccount findByPerson(final Person person){
        Assert.notNull(person);

        UserAccount result;

        result = this.userAccountRepository.findByPersonId(person.getId());

        return result;
    }

    public UserAccount save(UserAccount userAccount){
        Assert.notNull(userAccount);
        return userAccountRepository.save(userAccount);
    }
}
