package model;

import enums.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String userId;
    private Map<Channel, String> contactDetails;
    private List<Subscription> subscriptions;

    public User(String userId) {
        this.userId = userId;
        this.contactDetails = new HashMap<>();
        this.subscriptions = new ArrayList<>();
    }

    // getters
    public List<Subscription> getSubscriptions() { return subscriptions; }
    public Map<Channel, String> getContactDetails() { return contactDetails; }

    public void addSubscription(Subscription sub) {
        subscriptions.add(sub);
    }

    public void cancelSubscription(String subscriptionId) {
        for (Subscription sub : subscriptions) {
            if (sub.getSubscriptionId().equals(subscriptionId)) {
                sub.cancel();
                break;
            }
        }
    }
}
