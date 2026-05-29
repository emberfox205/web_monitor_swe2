package server;

import enums.Channel;
import enums.Frequency;
import model.Subscription;
import model.User;
import model.Website;
import server.service.Scheduler;
import server.service.NotificationService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerApp {
    private static Map<String, Website> websiteCache = new HashMap<>();

    public static void main(String[] args) throws Exception {

        // Mock user
        User preRegisteredUser = new User("user_001");
        preRegisteredUser.getContactDetails().put(Channel.EMAIL, "user@test.com");


        System.out.println("Starting Server on port 8080...");
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            NotificationService dispatcher = new NotificationService(out);
            Scheduler engine = new Scheduler(preRegisteredUser, dispatcher);
            new Thread(engine).start();

            out.println("Commands: ADD <url> <channel> <frequency> | CANCEL <id> |");

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] tokens = inputLine.split(" ");
                String command = tokens[0].toUpperCase();

                try {
                    switch (command) {
                        case "ADD":
                            String url = tokens[1];
                            Channel channel = Channel.valueOf(tokens[2].toUpperCase());
                            Frequency freq = Frequency.valueOf(tokens[3].toUpperCase());

                            Website site = websiteCache.computeIfAbsent(url, Website::new);
                            Subscription sub = new Subscription(site, freq, channel);
                            preRegisteredUser.addSubscription(sub);

                            out.println("Subscribed! ID: " + sub.getSubscriptionId());
                            break;
                        case "CANCEL":
                            preRegisteredUser.cancelSubscription(tokens[1]);
                            out.println("Cancelled subscription " + tokens[1]);
                            break;
                        default:
                            out.println("Unknown command.");
                    }
                } catch (Exception e) {
                    out.println("Error processing command.");
                }
            }
        }
    }
}
