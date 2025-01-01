
package domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends DomainEntity {

	//Constructors
	//******************************************************************************************************************


	public Person() {
		super();
		this.personAnnotations = new HashSet<>();
	}


	//Attributes
	//******************************************************************************************************************

	String					name;
	String					lastname;
	String					email;
	String					phone;
	int					cp;
	String					city;
	String					country;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
	this.name = name;
	}

	@NotBlank
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}


	@NotBlank
	@Pattern(regexp = "^(\\+\\d{1,2}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}


	public int getCp() {
		return this.cp;
	}

	public void setCp(final int cp) {
		this.cp = cp;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}




	//Relations
	//******************************************************************************************************************

	private Collection<Annotation>	personAnnotations;
	private UserAccount		userAccount;


	@OneToMany(mappedBy = "annotationPerson")
	public Collection<Annotation> getPersonAnnotations() {
		return this.personAnnotations;
	}

	public void setPersonAnnotations(final Collection<Annotation> personAnnotations) {
		this.personAnnotations = personAnnotations;
	}


	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
