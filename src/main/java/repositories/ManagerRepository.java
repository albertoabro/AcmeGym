
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	@Query("select m from Manager m where m.name = ?1")
	public Manager findByName(String name);
	@Query("select m from Manager m where m.email = ?1")
	public Manager findByEmail(String email);
	@Query("select m from Manager m where m.userAccount.id = ?1")
	Manager findByUserAccountId(int userAccountId);
	@Query("select m from Manager m where m.managerAdmin.id = ?1")
	Collection<Manager> findByIdAdmin(int idAdmin);
	@Query("select m from Manager  m where m.banned = 0")
	Collection<Manager> findAllActive();
}
