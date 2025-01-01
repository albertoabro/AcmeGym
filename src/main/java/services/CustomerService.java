
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Customer;
import repositories.CustomerRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerRepository	customerRepository;
	@Autowired
	RegistrationService	registrationService;


	public CustomerService() {
		super();
	}

	public Customer create() {
		return new Customer();
	}

	public Collection<Customer> findAll() {
		Collection<Customer> customers;

		customers = this.customerRepository.findAll();
		Assert.notNull(customers);

		return customers;
	}

	public Customer findOne(final int customerId) {
		Customer result;

		result = this.customerRepository.findOne(customerId);
		Assert.notNull(result);

		return result;
	}

	public Customer save(final Customer customer) {
		Assert.notNull(customer);
		customer.getUserAccount().setAccountNonLocked(true);
		return this.customerRepository.save(customer);
	}

	public void delete(final Customer customer) {
		Assert.notNull(customer);
		Assert.isTrue(customer.getId() != 0);

		this.customerRepository.delete(customer);
	}

	public Customer findByPrincipal() {
		Customer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Customer findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Customer result;

		result = this.customerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
}
