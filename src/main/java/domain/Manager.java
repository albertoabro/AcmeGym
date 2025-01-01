
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Person {

	//Constructors
	//******************************************************************************************************************

	public Manager() {
		super();
		this.managerGyms = new HashSet<>();
		this.banned = false;
	}

	public Manager(final boolean banned) {
		super();
		this.banned = banned;
		this.managerGyms = new HashSet<>();
	}

	//Relations


	//******************************************************************************************************************

	private boolean banned;


	@NotNull
	public boolean isBanned() {
		return this.banned;
	}

	public void setBanned(final boolean banned) {
		this.banned = banned;
	}


	//Relations
	//******************************************************************************************************************

	private Collection<Gym>	managerGyms;
	private Admin			managerAdmin;


	@NotNull
	@Valid
	@ManyToOne()
	public Admin getManagerAdmin() {
		return this.managerAdmin;
	}

	public void setManagerAdmin(final Admin managerAdmin) {
		this.managerAdmin = managerAdmin;
	}

	@NotEmpty
	@ManyToMany
	@JoinTable(name = "ManagementGym", joinColumns = @JoinColumn(name = "manager_id"), inverseJoinColumns = @JoinColumn(name = "gym_id"))
	public Collection<Gym> getManagerGyms() {
		return this.managerGyms;
	}

	public void setManagerGyms(final Collection<Gym> managerGyms) {
		this.managerGyms = managerGyms;
	}
}
