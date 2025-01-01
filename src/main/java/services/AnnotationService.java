package services;


import domain.Annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.AnnotationRepository;

import java.util.Collection;

@Service
@Transactional
public class AnnotationService {

    @Autowired
    AnnotationRepository annotationRepository;

    public AnnotationService() {
        super();
    }

    public Collection<Annotation> findByGymId(int gymId){
        Collection<Annotation>annotations;
        annotations = annotationRepository.findAnnotationByIdGym(gymId);
        Assert.notNull(annotations);
        return annotations;
    }

    public Collection<Annotation> findAll(){
        Collection<Annotation> annotations = annotationRepository.findAll();
        Assert.notNull(annotations);
        return annotations;
    }

    public Annotation create(){
        return new Annotation();
    }

    public Annotation save(Annotation annotation){
        Assert.notNull(annotation);
        return annotationRepository.save(annotation);
    }

    public void delete(Annotation annotation){
        Assert.notNull(annotation);
        Assert.isTrue(annotation.getId()!=0);
        annotationRepository.delete(annotation);
    }
}
