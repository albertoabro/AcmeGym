package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity{

    //Constructors
    //******************************************************************************************************************

    public SocialIdentity() {
        super();
        socialIdentitiesCV = new HashSet<>();
    }



    //Attributes
    //******************************************************************************************************************

    private String nick;
    private String url;
    private String name;


    @NotBlank
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    @NotBlank
    @URL
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    //Relations
    //******************************************************************************************************************

    private Collection<CV> socialIdentitiesCV;


    @NotEmpty
    @ManyToMany
    @JoinTable(
            name = "Identities",
            joinColumns = @JoinColumn(name="socialidentity_id"),
            inverseJoinColumns = @JoinColumn(name = "cv_id"))
    public Collection<CV> getSocialIdentitiesCV() {
        return socialIdentitiesCV;
    }

    public void setSocialIdentitiesCV(Collection<CV> socialIdentitiesCV) {
        this.socialIdentitiesCV = socialIdentitiesCV;
    }

}
