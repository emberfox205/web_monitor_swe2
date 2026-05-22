package service;

import enums.Channel;
import java.io.PrintWriter;

public class NotificationDispatcher {
    private PrintWriter clientOut;

    public NotificationDispatcher(PrintWriter clientOut) {
        this.clientOut = clientOut;
    }

    public void deliver(String contactDetail, Channel channel, String url) {
        String message = String.format("Website (%s) updated [%s]", url, channel.name());

        // to Server console
        System.out.println("Dispatched: " + message);

        // to client console
        if (clientOut != null) {
            clientOut.println("[NOTIFICATION] " + message);
        }
    }
}
