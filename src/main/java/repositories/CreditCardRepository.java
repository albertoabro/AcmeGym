package repositories;

import domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {

    @Query("select t from CreditCard t where t.owner = ?1")
    public CreditCard findByOwner(String owner);
    @Query("select t from CreditCard t where t.number = ?1")
    public CreditCard findByNumber(String number);
}
