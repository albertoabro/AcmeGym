package domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Access(AccessType.PROPERTY)
public class Trainer extends Person {

    //Constructors
    //******************************************************************************************************************

    public Trainer() {
        super();
        trainerCvs = new HashSet<>();
        trainerActivities = new HashSet<>();
        trainerGyms = new HashSet<>();
    }



    //Relations
    //******************************************************************************************************************

    private Collection<CV> trainerCvs;
    private Collection<Activity> trainerActivities;
    private Collection<Gym> trainerGyms;

    @OneToMany(mappedBy = "cvTrainer")
    public Collection<CV> getTrainerCvs() {
        return trainerCvs;
    }

    public void setTrainerCvs(Collection<CV> trainerCvs) {
        this.trainerCvs = trainerCvs;
    }


    @NotEmpty
    @ManyToMany
    @JoinTable(
            name = "TrainerActivity",
            joinColumns = @JoinColumn(name="trainer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "id"))
    public Collection<Activity> getTrainerActivities() {
        return trainerActivities;
    }

    public void setTrainerActivities(Collection<Activity> trainerActivities) {
        this.trainerActivities = trainerActivities;
    }


    @NotEmpty
    @ManyToMany
    @JoinTable(
            name = "TrainerGym",
            joinColumns = @JoinColumn(name="trainer_id"),
            inverseJoinColumns = @JoinColumn(name = "gym_id"))
    public Collection<Gym> getTrainerGyms() {
        return trainerGyms;
    }

    public void setTrainerGyms(Collection<Gym> trainerGyms) {
        this.trainerGyms = trainerGyms;
    }
}
