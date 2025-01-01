
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Gym extends DomainEntity {

	//Constructors
	//******************************************************************************************************************

	public Gym() {
		super();
		this.gymRegistrations = new HashSet<>();
		this.gymManagers = new HashSet<>();
		this.gymTrainers = new HashSet<>();
		this.gymTrainings = new HashSet<>();
		this.gymActivities = new HashSet<>();
		this.gymAdmins = new HashSet<>();
		this.deleted = false;
	}



	//Attributes
	//******************************************************************************************************************

	private String	name;
	private String	logo;
	private String	address;
	private float	price;
	private boolean	deleted;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@URL
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(final String logo) {
		this.logo = logo;
	}

	@NotBlank
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@NotNull
	@Min(0)
	public float getPrice() {
		return this.price;
	}

	public void setPrice(final float price) {
		this.price = price;
	}

	@NotNull
	public boolean isDeleted() {
		return this.deleted;
	}

	public void setDeleted(final boolean deleted) {
		this.deleted = deleted;
	}



	//Relations
	//******************************************************************************************************************

	private Collection<Manager>			gymManagers;
	private Collection<Registration>	gymRegistrations;
	private Collection<Training>		gymTrainings;
	private Collection<Activity>		gymActivities;
	private Collection<Admin>			gymAdmins;
	private Collection<Trainer>			gymTrainers;
	private Collection<Annotation>		gymAnnotations;

	@NotEmpty
	@ManyToMany(mappedBy = "managerGyms")
	public Collection<Manager> getGymManagers() {
		return gymManagers;
	}

	public void setGymManagers(Collection<Manager> gymManagers) {
		this.gymManagers = gymManagers;
	}

	@NotNull
	@OneToMany(mappedBy = "registrationGym")
	public Collection<Registration> getGymRegistrations() {
		return gymRegistrations;
	}

	public void setGymRegistrations(Collection<Registration> gymRegistrations) {
		this.gymRegistrations = gymRegistrations;
	}

	@ManyToMany(mappedBy = "trainingGyms")
	public Collection<Training> getGymTrainings() {
		return gymTrainings;
	}

	public void setGymTrainings(Collection<Training> gymTrainings) {
		this.gymTrainings = gymTrainings;
	}

	@NotNull
	@OneToMany(mappedBy = "activityGym")
	public Collection<Activity> getGymActivities() {
		return gymActivities;
	}

	public void setGymActivities(Collection<Activity> gymActivities) {
		this.gymActivities = gymActivities;
	}

	@NotNull
	@ManyToMany(mappedBy = "adminGyms")
	public Collection<Admin> getGymAdmins() {
		return gymAdmins;
	}

	public void setGymAdmins(Collection<Admin> gymAdmins) {
		this.gymAdmins = gymAdmins;
	}

	@NotNull
	@ManyToMany(mappedBy = "trainerGyms")
	public Collection<Trainer> getGymTrainers() {
		return gymTrainers;
	}

	public void setGymTrainers(Collection<Trainer> gymTrainers) {
		this.gymTrainers = gymTrainers;
	}

	@OneToMany(mappedBy = "annotationGym")
	public Collection<Annotation> getGymAnnotations() {
		return gymAnnotations;
	}

	public void setGymAnnotations(Collection<Annotation> gymAnnotations) {
		this.gymAnnotations = gymAnnotations;
	}
}
