package com.alura.literatura.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class GutendexAPI {

    public Optional<String> getDatos(String URL) {
        HttpClient client = null;
        HttpRequest request = null;
        HttpResponse<String> response = null;
        try {
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.of(response.body());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return Optional.empty();
    }
}
