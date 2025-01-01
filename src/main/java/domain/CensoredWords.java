
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class CensoredWords {

	//Constructors
	//******************************************************************************************************************

	public CensoredWords() {
		super();
	}

	public CensoredWords(final String word) {
		super();
		this.word = word;
	}



	//Attributes
	//******************************************************************************************************************

	String word;


	@NotBlank
	public String getCensored() { return this.word;}

	public void setCensored(final String word) { this.word = word; }

}
