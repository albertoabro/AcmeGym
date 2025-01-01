
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Integer> {

	@Query("select g from Gym g where g.name = ?1")
	public Gym findByName(String name);
	@Query("select g from Gym g join g.gymActivities a where a.id = ?1")
	public Collection<Gym> findByIdActivity(int idActivity);
	@Query("select g from Gym g join g.gymManagers gm where gm.id = ?1")
	public Collection<Gym> findByIdManager(int idManager);
}
