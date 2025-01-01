
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Activity extends DomainEntity {

	//Constructors
	//******************************************************************************************************************

	public Activity() {
		super();
		this.activityCustomers = new HashSet<>();
		this.activityTrainers = new HashSet<>();
		this.activityAnnotations = new HashSet<>();
		this.deleted = false;
	}



	//Attributes
	//******************************************************************************************************************

	private String 	title;
	private String 	photo;
	private String 	description;
	private String 	day;
	private String 	startingTime;
	private String	endingTime;
	private int		capacity;
	private boolean	deleted;



	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}


	@NotBlank
	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}


	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}


	@NotBlank
	public String getDay() {
		return this.day;
	}

	public void setDay(final String day) {
		this.day = day;
	}


	@NotBlank
	public String getStartingTime() {
		return this.startingTime;
	}

	public void setStartingTime(final String startingTime) {
		this.startingTime = startingTime;
	}


	@NotBlank
	public String getEndingTime() {
		return this.endingTime;
	}

	public void setEndingTime(final String endingTime) {
		this.endingTime = endingTime;
	}


	@NotNull
	@Min(0)
	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(final int capacity) {
		this.capacity = capacity;
	}


	@NotNull
	public boolean isDeleted() {
		return this.deleted;
	}

	public void setDeleted(final boolean deleted) {
		this.deleted = deleted;
	}




	// Relations
	//******************************************************************************************************************

	private Collection<Trainer> 	activityTrainers;
	private Collection<Customer>	activityCustomers;
	private Gym						activityGym;
	private Collection<Annotation>	activityAnnotations;




	@ManyToMany(mappedBy = "trainerActivities")
	public Collection<Trainer> getActivityTrainers() {
		return this.activityTrainers;
	}

	public void setActivityTrainers(final Collection<Trainer> activityTrainers) {
		this.activityTrainers = activityTrainers;
	}



	@ManyToMany(mappedBy = "customerActivities")
	public Collection<Customer> getActivityCustomers() {
		return this.activityCustomers;
	}

	public void setActivityCustomers(final Collection<Customer> activityCustomers) {
		this.activityCustomers = activityCustomers;
	}


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Gym getActivityGym() {
		return this.activityGym;
	}

	public void setActivityGym(final Gym activityGym) {
		this.activityGym = activityGym;
	}


	@OneToMany(mappedBy = "annotationActivity")
	public Collection<Annotation> getActivityAnnotations() {
		return this.activityAnnotations;
	}

	public void setActivityAnnotations(final Collection<Annotation> activityAnnotations) {
		this.activityAnnotations = activityAnnotations;
	}
}
