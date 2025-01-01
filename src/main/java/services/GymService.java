
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Annotation;
import domain.Gym;
import repositories.GymRepository;

@Service
@Transactional
public class GymService {

	@Autowired
	GymRepository		gymRepository;
	@Autowired
	AnnotationService	annotationService;
	@Autowired
	ManagerService		managerService;


	public Collection<Gym> findAll() {

		final Collection<Gym> gyms = this.gymRepository.findAll();
		Assert.notNull(gyms);
		return gyms;
	}

	public Gym findOne(final int gymId) {
		Assert.notNull(gymId);
		final Gym gym = this.gymRepository.findOne(gymId);
		Assert.notNull(gym);

		return gym;
	}

	public Gym create() {
		return new Gym();
	}

	public Gym save(final Gym gym) {
		Assert.notNull(gym);

		return this.gymRepository.save(gym);
	}

	public void delete(final int gymId) {
		final Gym gym = this.findOne(gymId);
		gym.setDeleted(true);
	}

	public Collection<Gym> findByIdActivity(final int idActivity) {
		Assert.notNull(idActivity);
		final Collection<Gym> gyms = this.gymRepository.findByIdActivity(idActivity);
		Assert.notNull(gyms);
		return gyms;
	}

	public Collection<Gym> findByIdManager(final int idManager) {
		Assert.notNull(idManager);
		final Collection<Gym> gyms = this.gymRepository.findByIdManager(idManager);
		Assert.notNull(gyms);
		return gyms;
	}
}
