package repositories;

import domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("select p from Person p where p.userAccount.id = ?1")
    public Person findByIdAccount(int idAccount);
}
