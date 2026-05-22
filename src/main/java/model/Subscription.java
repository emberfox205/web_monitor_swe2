package model;

import enums.Channel;
import enums.Frequency;
import java.time.LocalDateTime;
import java.util.UUID;

public class Subscription {
    private String subscriptionId;
    private Website website;
    private Frequency checkFrequency;
    private Channel communicationChannel;
    private LocalDateTime nextCheckDue;
    private boolean isActive;

    public Subscription(Website website, Frequency frequency, Channel channel) {
        this.subscriptionId = UUID.randomUUID().toString().substring(0, 8);
        this.website = website;
        this.checkFrequency = frequency;
        this.communicationChannel = channel;
        this.isActive = true;
        calculateNextCheckTime();
    }

    public String getSubscriptionId() { return subscriptionId; }
    public Website getWebsite() { return website; }
    public Channel getCommunicationChannel() { return communicationChannel; }
    public LocalDateTime getNextCheckDue() { return nextCheckDue; }
    public boolean isActive() { return isActive; }

    public void updatePreferences(Frequency frequency, Channel channel) {
        this.checkFrequency = frequency;
        this.communicationChannel = channel;
        calculateNextCheckTime();
    }

    public void cancel() {
        this.isActive = false;
    }

    public void calculateNextCheckTime() {
        this.nextCheckDue = LocalDateTime.now().plusSeconds(checkFrequency.getSeconds());
    }
}
