
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	@Query("select t from Trainer t where t.name = ?1")
	public Trainer findByName(String name);
	@Query("select t from Trainer t where t.email = ?1")
	public Trainer findByEmail(String email);
	@Query("select t from Trainer t join t.trainerActivities a where a.id = ?1")
	public Collection<Trainer> findTrainerByIdActivity(int idActivity);
	@Query("select t from Trainer t join t.trainerGyms tg join tg.gymManagers tgm where tgm.id = ?1")
	public Collection<Trainer>findByIdManager(int idManager);
	@Query("select  t from Trainer t where t.name like ?1 or t.lastname like ?1")
	public Collection<Trainer> findByWord(String word);
}
