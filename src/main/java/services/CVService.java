package services;


import domain.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.CVRepository;

import java.util.Collection;

@Service
@Transactional
public class CVService {
    @Autowired
    CVRepository cvRepository;

    public CVService() {
        super();
    }

    public Collection<CV> findAll(){
        Collection<CV>cvs = cvRepository.findAll();
        Assert.notNull(cvs);
        return cvs;
    }

    public CV findOne(int cvId){
        Assert.notNull(cvId);
        CV cv = cvRepository.findOne(cvId);
        Assert.notNull(cv);
        return cv;
    }

    public CV create(){
        return new CV();
    }

    public CV save(CV cv){
        Assert.notNull(cv);
        return cvRepository.save(cv);
    }
}
