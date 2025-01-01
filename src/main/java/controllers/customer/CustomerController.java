package controllers.customer;

import controllers.AbstractController;
import domain.Activity;
import domain.CreditCard;
import domain.Customer;
import domain.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.Authority;
import services.CustomerService;
import services.PersonService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView modelAndView;
        Collection<Customer> customers;


        customers = this.customerService.findAll();

        modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("requestUri", "customer/list.do");
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("permission", true);

        return modelAndView;
    }

    @RequestMapping(value = "/create")
    public ModelAndView create() {
        ModelAndView modelAndView;
        final Customer customer = this.customerService.create();

        modelAndView = this.createEditModelAndView(customer);
        return modelAndView;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
    public ModelAndView save(@Valid final Customer customer, final BindingResult binding) {
        ModelAndView modelAndView;

        if (binding.hasErrors())
            modelAndView = this.createEditModelAndView(customer, binding.toString());
        else
            try {
                this.customerService.save(customer);
                modelAndView = this.list();
            } catch (final Throwable oops) {
                modelAndView = this.createEditModelAndView(customer, "profile.commit.error");
            }

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
    public ModelAndView create(@Valid final Customer customer, final BindingResult binding) {
        ModelAndView modelAndView;

        if (binding.hasErrors())
            modelAndView = this.createEditModelAndView(customer, binding.toString());
        else
            try {
                customer.getUserAccount().setPassword(this.personService.toMD5(customer.getUserAccount().getPassword()));
                this.customerService.save(customer);
                modelAndView = this.list();
            } catch (final Throwable oops) {
                modelAndView = this.createEditModelAndView(customer, "profile.commit.error");
            }

        return modelAndView;
    }
    private ModelAndView createEditModelAndView(final Customer customer) {
        final ModelAndView modelAndView = this.createEditModelAndView(customer, null);
        return modelAndView;
    }

    private ModelAndView createEditModelAndView(final Customer customer, String message) {
        ModelAndView result;

        Collection<Activity> activities = customer.getCustomerActivities();
        Collection<Registration> registrations = customer.getCustomerRegistrations();
        Collection<CreditCard> creditCards = customer.getCustomerCreditCards();

        final Authority authorityCustomer = new Authority();
        authorityCustomer.setAuthority(Authority.CUSTOMER);
        final Authority authorityManager = new Authority();
        authorityManager.setAuthority(Authority.MANAGER);
        final Collection<Authority> authorities = new HashSet<>();
        authorities.add(authorityCustomer);
        authorities.add(authorityManager);

        result = new ModelAndView("customer/edit");
        result.addObject("customer", customer);
        result.addObject("activities", activities);
        result.addObject("registrations", registrations);
        result.addObject("authority", authorities);
        result.addObject("creditCards", creditCards);
        result.addObject("message", message);
        return result;
    }
}
