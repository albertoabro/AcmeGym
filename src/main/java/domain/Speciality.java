package domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Speciality {

    //Constructors
    //******************************************************************************************************************

    public Speciality() {
        super();
    }



    //Attributes
    //******************************************************************************************************************

    private String speciality;


    @NotBlank
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}
