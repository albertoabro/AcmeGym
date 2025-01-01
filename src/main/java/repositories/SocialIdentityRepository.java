package repositories;

import domain.SocialIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialIdentityRepository extends JpaRepository<SocialIdentity, Integer>{
    @Query("select i from SocialIdentity i where i.id = ?1")
    public SocialIdentity findById(int id);
    @Query("select i from SocialIdentity i where i.nick = ?1")
    public SocialIdentity findByNick(String nick);
    @Query("select i from SocialIdentity i")
    public List<SocialIdentity> findAll();
}
