package repositories;

import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.name = ?1")
    public Customer findByName(String name);
    @Query("select c from Customer c where c.email = ?1")
    public Customer findByEmail(String email);
    @Query("select c from Customer c where c.userAccount.id = ?1")
    Customer findByUserAccountId(int userAccountId);
}
