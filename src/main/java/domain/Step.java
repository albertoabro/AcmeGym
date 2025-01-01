
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Step extends DomainEntity {

	//Constructors
	//******************************************************************************************************************

	public Step() {
		super();
		this.stepTrainings = new HashSet<>();
	}


	//Attributes
	//******************************************************************************************************************

	private String	title;
	private String	tutorial;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@URL
	public String getTutorial() {
		return this.tutorial;
	}

	public void setTutorial(final String tutorial) {
		this.tutorial = tutorial;
	}


	//Relations
	//******************************************************************************************************************

	private Collection<Training> stepTrainings;


	@ManyToMany
	@JoinTable(name = "StepTraining", joinColumns = @JoinColumn(name = "step_id"), inverseJoinColumns = @JoinColumn(name = "training_id"))
	public Collection<Training> getStepTrainings() {
		return this.stepTrainings;
	}

	public void setStepTrainings(final Collection<Training> trainings) {
		this.stepTrainings = trainings;
	}

}
