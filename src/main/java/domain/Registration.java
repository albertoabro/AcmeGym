
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Registration extends DomainEntity {

	//Constructors
	//******************************************************************************************************************

	public Registration() {
		super();
		this.registrationBills = new HashSet<>();
	}



	//Constructors
	//******************************************************************************************************************

	private Collection<Bill> registrationBills;
	private Customer		 registrationCustomer;
	private Gym				 registrationGym;


	@NotNull
	@OneToMany(mappedBy = "billRegistration")
	public Collection<Bill> getRegistrationBills() {
		return this.registrationBills;
	}

	public void setRegistrationBills(final Collection<Bill> registrationBills) {
		this.registrationBills = registrationBills;
	}


	@NotNull
	@Valid
	@ManyToOne()
	public Customer getRegistrationCustomer() {
		return this.registrationCustomer;
	}

	public void setRegistrationCustomer(final Customer registrationCustomer) {
		this.registrationCustomer = registrationCustomer;
	}


	@NotNull
	@ManyToOne
	public Gym getRegistrationGym() {
		return this.registrationGym;
	}

	public void setRegistrationGym(final Gym registrationGym) {
		this.registrationGym = registrationGym;
	}

}
