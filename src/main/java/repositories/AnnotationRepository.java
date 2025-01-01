
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Annotation;

@Repository
public interface AnnotationRepository extends JpaRepository<Annotation, Integer> {

	@Query("select a from Annotation a where a.annotationGym.id =?1")
	Collection<Annotation> findAnnotationByIdGym(int idGym);
}
