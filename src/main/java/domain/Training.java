
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Training extends DomainEntity {

	//Constructors
	//******************************************************************************************************************

	public Training() {
		super();
		this.trainingSteps = new HashSet<>();
		this.trainingGyms = new HashSet<>();
		this.trainingAnnotations = new HashSet<>();
	}


	//Attributes
	//******************************************************************************************************************

	private String	title;
	private String	description;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}


	//Relations
	//******************************************************************************************************************

	private Collection<Step>		trainingSteps;
	private Collection<Gym>			trainingGyms;
	private Collection<Annotation>	trainingAnnotations;


	@ManyToMany(mappedBy = "stepTrainings")
	public Collection<Step> getTrainingSteps() {
		return this.trainingSteps;
	}

	public void setTrainingSteps(final Collection<Step> trainingSteps) {
		this.trainingSteps = trainingSteps;
	}

	@NotEmpty
	@ManyToMany
	@JoinTable(name = "TrainingGym", joinColumns = @JoinColumn(name = "training_id"), inverseJoinColumns = @JoinColumn(name = "gym_id"))
	public Collection<Gym> getTrainingGyms() {
		return this.trainingGyms;
	}

	public void setTrainingGyms(final Collection<Gym> trainingGyms) {
		this.trainingGyms = trainingGyms;
	}

	@OneToMany(mappedBy = "annotationTraining")
	public Collection<Annotation> getTrainingAnnotations() {
		return this.trainingAnnotations;
	}

	public void setTrainingAnnotations(final Collection<Annotation> trainingAnnotations) {
		this.trainingAnnotations = trainingAnnotations;
	}

}
