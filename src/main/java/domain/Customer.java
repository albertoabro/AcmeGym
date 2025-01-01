
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
public class Customer extends Person {

	//Constructors
	//******************************************************************************************************************

	public Customer() {
		super();
		this.customerRegistrations = new HashSet<>();
		this.customerActivities = new HashSet<>();
		this.customerCreditCards = new HashSet<>();
	}

	//Attributes
	//******************************************************************************************************************



	//Relations
	//******************************************************************************************************************

	Collection<Registration>	customerRegistrations;
	Collection<Activity>		customerActivities;
	Collection<CreditCard>		customerCreditCards;


	@NotEmpty
	@OneToMany(mappedBy = "registrationCustomer")
	public Collection<Registration> getCustomerRegistrations() {
		return this.customerRegistrations;
	}
	public void setCustomerRegistrations(final Collection<Registration> customerRegistrations) {
		this.customerRegistrations = customerRegistrations;
	}

	@NotEmpty
	@ManyToMany
	@JoinTable(name = "CustomerActivity", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "activity_id", referencedColumnName = "id"))
	public Collection<Activity> getCustomerActivities() {
		return this.customerActivities;
	}

	public void setCustomerActivities(final Collection<Activity> customerActivities) {
		this.customerActivities = customerActivities;
	}

	@NotEmpty
	@OneToMany(mappedBy = "creditCardCustomer")
	public Collection<CreditCard> getCustomerCreditCards() {
		return this.customerCreditCards;
	}

	public void setCustomerCreditCards(final Collection<CreditCard> customerCreditCards) {
		this.customerCreditCards = customerCreditCards;
	}
}
