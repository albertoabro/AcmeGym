package domain;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

    //Constructors
    //******************************************************************************************************************

    public CreditCard() {
        super();
    }



    //Attributes
    //******************************************************************************************************************

    private String owner;
    private String tradeMark;
    private String number;
    private int month;
    private int year;
    private int cvv;



    @NotBlank
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    @NotBlank
    public String getTradeMark() {
        return tradeMark;
    }
    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark;
    }


    @NotBlank
    @CreditCardNumber
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    @NotNull
    @Range(min = 1, max = 12)
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }


    @NotNull
    @Min(2023)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @NotNull
    @Range(min = 100, max = 999)
    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }




    //Relations
    //******************************************************************************************************************

    private Customer creditCardCustomer;

    @NotNull
    @Valid
    @ManyToOne(optional = false)
    public Customer getCreditCardCustomer(){return creditCardCustomer;}

    public void setCreditCardCustomer(Customer creditCardCustomer) {
        this.creditCardCustomer = creditCardCustomer;
    }
}
