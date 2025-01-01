package repositories;

import domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    @Query("select a from Activity a where a.title = ?1")
    public Activity findByTitle(String title);
    @Query("select a from Activity a where a.deleted = 0")
    public Collection<Activity> findAllActive();
    @Query("select a from Activity a where a.activityGym.id=?1")
    public Collection<Activity> findByGymId(int idGym);
    @Query("select a from Activity a where a.activityGym.id=?1 and a.deleted = 0")
    public Collection<Activity> findActiveByGymId(int idGym);
    @Query("select a from Activity a join a.activityGym ag join ag.gymManagers m where m.id =?1")
    public Collection<Activity>findByIdManager(int idManager);
    @Query("select a from Activity a where (a.title like ?1 or a.description like ?1) and a.day = ?2 and a.startingTime = ?3")
    public Collection<Activity> findByFilter(String word, String day, String time);
}
