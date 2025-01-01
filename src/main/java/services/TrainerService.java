
package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Trainer;
import repositories.TrainerRepository;
import security.UserAccountService;

@Service
@Transactional
public class TrainerService {

	@Autowired
	TrainerRepository	trainerRepository;
	@Autowired
	UserAccountService	userAccountService;


	public TrainerService() {
		super();
	}

	public Collection<Trainer> findAll() {
		final Collection<Trainer> trainers = this.trainerRepository.findAll();
		Assert.notNull(trainers);
		return trainers;
	}

	public Trainer create() {
		return new Trainer();
	}

	public Trainer save(final Trainer trainer) {
		Assert.notNull(trainer);
		trainer.getUserAccount().setAccountNonLocked(true);
		return this.trainerRepository.save(trainer);
	}

	public Collection<Trainer> findTrainerByIdActivity(final int idActivity) {
		Assert.notNull(idActivity);
		final Collection<Trainer> trainers = this.trainerRepository.findTrainerByIdActivity(idActivity);
		Assert.notNull(trainers);
		return trainers;
	}

	public Trainer findOne(final int idPerson) {
		Assert.notNull(idPerson);
		final Trainer trainer = this.trainerRepository.findOne(idPerson);
		Assert.notNull(trainer);
		return trainer;
	}

	public Collection<Trainer> findByIdManager(final int idManager) {
		Assert.notNull(idManager);
		final Collection<Trainer> trainers = this.trainerRepository.findByIdManager(idManager);
		return trainers;
	}

	public Collection<Trainer> findByWord(final String word) {
		Assert.notNull(word);
		final String character = "%";
		final String newWord = character + word + character;

		return this.trainerRepository.findByWord(newWord);
	}
}
