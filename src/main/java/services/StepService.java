package services;


import domain.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.StepRepository;

import java.util.Collection;

@Service
@Transactional
public class StepService {

    @Autowired
    StepRepository stepRepository;

    public StepService() {
        super();
    }

    public Collection<Step> findAll(){
        return stepRepository.findAll();
    }
}
