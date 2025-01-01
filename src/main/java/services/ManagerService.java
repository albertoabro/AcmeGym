package services;


import domain.Customer;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ManagerRepository;
import security.LoginService;
import security.UserAccount;

import java.util.Collection;
public class ManagerService {

	@Autowired
	ManagerRepository managerRepository;


	public Manager findByPrincipal() {
		Manager manager;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		manager = this.findByUserAccount(userAccount);
		Assert.notNull(manager);

		return manager;
	}
	public Collection<Manager> findAll() {

		Collection<Manager> result;

		Assert.notNull(this.managerRepository);
		result = this.managerRepository.findAll();
		Assert.notNull(result);

		return result;
	}
	public Manager findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Manager manager;

		manager = this.managerRepository.findByUserAccountId(userAccount.getId());

		return manager;
	}

	public Manager create() {
		return new Manager();
	}

	public Manager save(final Manager manager) {
		Assert.notNull(manager);
		manager.getUserAccount().setAccountNonLocked(true);
		return this.managerRepository.save(manager);
	}

	public Manager findOne(final int idManager) {
		Assert.notNull(idManager);
		final Manager manager = this.managerRepository.findOne(idManager);
		Assert.notNull(manager);
		return manager;
	}

	public Collection<Manager> findByAdminId(final int idAdmin) {
		Assert.notNull(idAdmin);
		final Collection<Manager> managers = this.managerRepository.findByIdAdmin(idAdmin);
		Assert.notNull(managers);
		return managers;
	}
	public Collection<Manager> findAllActive(){
		return managerRepository.findAllActive();
	}
}
