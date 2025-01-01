
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Admin;
import domain.Manager;
import repositories.AdminRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdminService {

	@Autowired
	AdminRepository	adminRepository;
	@Autowired
	ManagerService	managerService;


	public AdminService() {
		super();
	}

	public void banManager(final Manager manager) {
		Assert.notNull(manager);
		Assert.isTrue(manager.getId() != 0);
		manager.setBanned(true);
		this.managerService.save(manager);
	}

	public void unBanManager(final Manager manager) {
		Assert.notNull(manager);
		Assert.isTrue(manager.getId() != 0);
		manager.setBanned(false);
		this.managerService.save(manager);
	}

	public Admin findOne(final int idPerson) {
		Assert.notNull(idPerson);
		final Admin admin = this.adminRepository.findOne(idPerson);

		return admin;
	}

	public Admin save(final Admin person) {
		Assert.notNull(person);
		person.getUserAccount().setAccountNonLocked(true);
		return this.adminRepository.save(person);
	}

	public Admin findByPrincipal() {
		Admin admin;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		admin = this.findByUserAccount(userAccount);
		Assert.notNull(admin);

		return admin;
	}
	public Admin findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Admin admin;

		admin = this.adminRepository.findByUserAccountId(userAccount.getId());

		return admin;
	}

	public Collection<Admin> findAll() {
		return this.adminRepository.findAll();
	}
}
