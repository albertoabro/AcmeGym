package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.SocialIdentityRepository;

@Service
@Transactional
public class SocialIdentityService {
    @Autowired
    SocialIdentityRepository socialIdentityRepository;

    public SocialIdentityService() {
        super();
    }


}
