package server.service;

import model.Subscription;
import model.User;
import model.Website;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Scheduler implements Runnable {
    private User mockUser;
    private NotificationService dispatcher;

    public Scheduler(User mockUser, NotificationService dispatcher) {
        this.mockUser = mockUser;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000); // Check loop runs every 2 seconds
                executeChecks();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void executeChecks() {
        LocalDateTime now = LocalDateTime.now();
        List<Subscription> dueSubscriptions = mockUser.getSubscriptions().stream()
                .filter(Subscription::isActive)
                .filter(s -> s.getNextCheckDue().isBefore(now) || s.getNextCheckDue().isEqual(now))
                .collect(Collectors.toList());

        for (Subscription sub : dueSubscriptions) {
            Website site = sub.getWebsite();
            String freshContent = site.fetchCurrentContent();

            if (site.hasChanged(freshContent)) {
                String contact = mockUser.getContactDetails().get(sub.getCommunicationChannel());
                dispatcher.deliver(contact, sub.getCommunicationChannel(), site.getUrl());
            }
            sub.calculateNextCheckTime();
        }
    }
}
