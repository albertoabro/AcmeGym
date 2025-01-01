
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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Person {


	//Constructors
	//*************************************************************************************

	public Admin() {
		super();
		this.adminManagers = new HashSet<>();
		this.adminGyms = new HashSet<>();
	}



	//Relations
	//*************************************************************************************

	private Collection<Manager>	adminManagers;
	private Collection<Gym>		adminGyms;


	@NotEmpty
	@OneToMany(mappedBy = "managerAdmin")
	public Collection<Manager> getAdminManagers() {
		return this.adminManagers;
	}

	public void setAdminManagers(final Collection<Manager> adminManagers) {
		this.adminManagers = adminManagers;
	}


	@NotEmpty
	@ManyToMany
	@JoinTable(name = "AdminGym", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "gym_id"))
	public Collection<Gym> getAdminGyms() {
		return this.adminGyms;
	}

	public void setAdminGyms(final Collection<Gym> adminGyms) {
		this.adminGyms = adminGyms;
	}
}
