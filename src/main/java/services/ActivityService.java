package services;

import domain.Activity;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ActivityRepository;

import java.util.Collection;

@Service
@Transactional
public class ActivityService {

    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    CustomerService customerService;

    public ActivityService() {
        super();
    }

    public Activity findOne(int activityId) {
        Assert.notNull(activityId);
        Activity activity = activityRepository.findOne(activityId);
        Assert.notNull(activity);
        return activity;
    }

    public Activity create() {
        return new Activity();
    }

    public Activity save(Activity activity) {
        return null;
    }

    public void cancel(int activityId) {
        Activity activity = findOne(activityId);
        activity.setDeleted(true);
        save(activity);
    }

    public Collection<Activity> addCustomer(Activity activity) {
        Assert.notNull(activity);
        Customer customer = customerService.findByPrincipal();
        Collection<Activity> activities = customer.getCustomerActivities();
        activities.add(activity);
        customer.setCustomerActivities(activities);
        customerService.save(customer);
        return activities;
    }

    public Collection<Activity> dropOutCustomer(Activity activity) {
        Assert.notNull(activity);
        Customer customer = customerService.findByPrincipal();
        Collection<Activity> activities = customer.getCustomerActivities();
        Assert.isTrue(activities.contains(activity));
        activities.remove(activity);
        customerService.save(customer);
        return activities;
    }

    public Collection<Activity> findByIdGym(int idGym) {
        Assert.notNull(idGym);
        Collection<Activity> activities = activityRepository.findByGymId(idGym);
        Assert.notNull(activities);
        return activities;
    }

    public Collection<Activity> findAll() {
        Collection<Activity> activities = activityRepository.findAll();
        Assert.notNull(activities);
        return activities;
    }

    public Collection<Activity> findActiveByIdGym(int idGym) {
        Assert.notNull(idGym);
        Collection<Activity> activities = activityRepository.findActiveByGymId(idGym);
        Assert.notNull(activities);
        return activities;
    }
    public Collection<Activity> findAllActive() {
        Collection<Activity> activities = activityRepository.findAllActive();
        Assert.notNull(activities);
        return activities;
    }

    public Collection<Activity> findByIdManager(int idManager){
        Assert.notNull(idManager);
        Collection<Activity>activities = activityRepository.findByIdManager(idManager);
        Assert.notNull(activities);
        return activities;
    }

    public Collection<Activity> findByFilter(String word, String day, String time){
        Assert.notNull(word);
        Assert.notNull(day);
        Assert.notNull(time);

        Collection<Activity>activities = activityRepository.findByFilter(word, day, time);
        return activities;
    }
}