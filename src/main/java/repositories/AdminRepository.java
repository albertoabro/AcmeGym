
package repositories;

import domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a where a.name = ?1")
	public Admin findByName(String name);
	@Query("select a from Admin a where a.email = ?1")
	public Admin findByEmail(String email);
	@Query("select a from Admin a where a.userAccount.id = ?1")
    public Admin findByUserAccountId(int id);
}
