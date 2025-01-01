package services;


import domain.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.TrainingRepository;

import java.util.Collection;

@Service
@Transactional
public class TrainingService {
    @Autowired
    TrainingRepository trainingRepository;

    public TrainingService() {
        super();
    }

    public Collection<Training> findAll(){
        Collection<Training>trainings = trainingRepository.findAll();
        Assert.notNull(trainings);
        return trainings;
    }

    public Training create(){
        return new Training();
    }

    public Training save(Training training){
        Assert.notNull(training);
        return trainingRepository.save(training);
    }

    public void delete(Training training){
        Assert.notNull(training);
        Assert.isTrue(training.getId()!=0);
        trainingRepository.delete(training);
    }

    public Collection<Training> findByWord(String word){
        Assert.notNull(word);
        String character = "%";
        String newWord = character+word+character;
        return trainingRepository.findByWord(newWord);
    }

    public Collection<Training> findByIdManager(int idManager){
        Assert.notNull(idManager);
        Collection<Training> trainings = trainingRepository.findByIdManger(idManager);
        return trainings;
    }

    public Training findOne(int idTraining) {
        Assert.notNull(idTraining);
        return trainingRepository.findOne(idTraining);
    }
}
