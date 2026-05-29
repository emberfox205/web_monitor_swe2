package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

// WARNING: init website load should not be counted

public class Website implements Publisher {
    private String url;
    private String contentHash;
    Map<String, Subscription> subsCollection;

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public Website(String url) {
        this.url = url;
        this.contentHash = fetchContent();
        subsCollection = new HashMap<>();
    }

    public String getUrl() { return url; }

    public String fetchContent() {
        try {
            // HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.url))
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)") // circumvent bot detection
                    .HEAD() // reduce needed download payload
                    .build();

            // HTTP content
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String htmlContent = response.body();

            // hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(htmlContent.getBytes("UTF-8"));

            // bin -> readable hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                // 0 = padding, 2 = length of 2, x = hex
                sb.append(String.format("%02x", b));
            }
            return sb.toString(); // form: 32-bit str

        } catch (Exception e) {
            System.out.println("System Error fetching URL [" + this.url + "]: " + e.getMessage());
            // return the prev hash on failure
            return this.contentHash != null ? this.contentHash : "ERROR";
        }
    }

    public void attach(String subID, Subscription sub) {
        subsCollection.put(subID, sub);
    }

    public void detach(String subID) {
        subsCollection.remove(subID);
    }
}