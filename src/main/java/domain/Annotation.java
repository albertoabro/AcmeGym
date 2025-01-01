
package domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Annotation extends DomainEntity {


	//Constructors
	//*************************************************************************************

	public Annotation() {
		super();
		this.censored = new HashSet<>();
	}



	//Attributes
	//*************************************************************************************

	private String						text;
	private Date						date;
	private int							score;
	private Collection<CensoredWords>	censored;



	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}


	@Range(min = 0, max = 3)
	public int getScore() {
		return this.score;
	}

	public void setScore(final int score) {
		this.score = score;
	}


	@ElementCollection
	public Collection<CensoredWords> getCensored() {
		return this.censored;
	}

	public void setCensored(final Collection<CensoredWords> censored) {
		this.censored = censored;
	}



	//Relations
	//*************************************************************************************

	private Person		annotationPerson;

	// Every annotation should link to only one of these entities
	// The rest of them should remain Null
	private Activity	annotationActivity;
	private Gym			annotationGym;
	private Training	annotationTraining;



	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Person getAnnotationPerson() {
		return this.annotationPerson;
	}

	public void setAnnotationPerson(final Person annotationPerson) {
		this.annotationPerson = annotationPerson;
	}


	@Valid
	@ManyToOne()
	public Activity getAnnotationActivity() {
		return this.annotationActivity;
	}

	public void setAnnotationActivity(final Activity activity) {
		this.annotationActivity = activity;
	}


	@Valid
	@ManyToOne()
	public Gym getAnnotationGym() {
		return this.annotationGym;
	}

	public void setAnnotationGym(final Gym gymAnnotation) {
		this.annotationGym = gymAnnotation;
	}


	@Valid
	@ManyToOne()
	public Training getAnnotationTraining() {
		return this.annotationTraining;
	}

	public void setAnnotationTraining(final Training training) {
		this.annotationTraining = training;
	}

}
