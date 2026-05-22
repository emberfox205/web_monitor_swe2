package model;

import java.time.LocalDateTime;

public class Website {
    private String url;
    private String lastKnownStateHash;
    private LocalDateTime lastChecked;

    public Website(String url) {
        this.url = url;
        this.lastKnownStateHash = fetchCurrentContent();
        this.lastChecked = LocalDateTime.now();
    }

    public String getUrl() { return url; }

    public String fetchCurrentContent() {
        // Mock content
        return "State_" + (int)(Math.random() * 5);
    }

    public boolean hasChanged(String newStateHash) {
        boolean changed = !this.lastKnownStateHash.equals(newStateHash);
        if (changed) {
            this.lastKnownStateHash = newStateHash;
        }
        this.lastChecked = LocalDateTime.now();
        return changed;
    }
}