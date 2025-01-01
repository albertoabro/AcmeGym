
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class CV extends DomainEntity {

//Constructors
//******************************************************************************************************************


	public CV() {
		super();
		specialties = new HashSet<>();
		cvSocialIdentities = new HashSet<>();
	}



	//Attributes
	//******************************************************************************************************************

	private String						name;
	private Collection<Speciality> 				specialties;
	private String						experience;
	private String						training;
	private Trainer						cvTrainer;

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	@ElementCollection
	public Collection<Speciality> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(Collection<Speciality> specialties) {
		this.specialties = specialties;
	}


	@NotBlank
	public String getExperience() {
		return this.experience;
	}

	public void setExperience(final String experience) {
		this.experience = experience;
	}


	@NotBlank
	public String getTraining() {
		return this.training;
	}

	public void setTraining(final String training) {
		this.training = training;
	}



	//Relations
	//******************************************************************************************************************

	private Collection<SocialIdentity>	cvSocialIdentities;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Trainer getCvTrainer() {
		return this.cvTrainer;
	}

	public void setCvTrainer(final Trainer cvTrainer) {
		this.cvTrainer = cvTrainer;
	}


	@ManyToMany(mappedBy = "socialIdentitiesCV")
	public Collection<SocialIdentity> getCvSocialIdentities() {
		return this.cvSocialIdentities;
	}

	public void setCvSocialIdentities(Collection<SocialIdentity> cvSocialIdentities) {
		this.cvSocialIdentities = cvSocialIdentities;
	}
}
