
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Training;

import java.util.Collection;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {

	@Query("select t from Training t where t.title = ?1")
	public Training findByName(String name);
	@Query("select t from Training t where t.title like ?1 or t.description like ?1")
	public Collection<Training> findByWord(String word);
	@Query("select t from Training t join t.trainingGyms tt join tt.gymManagers ttm where ttm.id = ?1")
	public Collection<Training> findByIdManger(int idManager);
}
