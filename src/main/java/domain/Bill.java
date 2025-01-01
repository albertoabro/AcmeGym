package domain;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Bill extends DomainEntity{

    //Constructors
    //******************************************************************************************************************

    public Bill() {
        super();
    }



    //Attributes
    //******************************************************************************************************************

    private Date date;
    private String VAT;
    private int trimester;
    private int year;
    private float amount;
    private boolean deleted;



    @NotNull
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @NotBlank
    public String getVAT() {
        return VAT;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }


    @NotNull
    @Range(min = 1, max = 4)
    public int getTrimester() {
        return trimester;
    }

    public void setTrimester(int trimester) {
        this.trimester = trimester;
    }


    @NotNull
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @NotNull
    @Min(0)
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }


    @NotNull
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }



    //Relations
    //******************************************************************************************************************

    Registration billRegistration;

    @NotNull
    @Valid
    @ManyToOne()
    public Registration getBillRegistration(){return billRegistration;}

    public void setBillRegistration(Registration billRegistration) {
        this.billRegistration = billRegistration;
    }

}

