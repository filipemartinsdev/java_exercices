package com.javaexercices.term;

//import com.google.gson.Gson;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WordApiClient {
    private static final String API_WEBSERVICE = "https://random-word-api.herokuapp.com/word?lang=pt-br";

    public static String getRandomWord(int length) throws IOException, InterruptedException {
        String[] apiResponse = request(length);
        return apiResponse[0];
    }

    private static String[] request(int length) throws IOException, InterruptedException {
        String newApiURL = API_WEBSERVICE+"&length="+length;

        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(newApiURL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        return gson.fromJson(response.body(), String[].class);
    }
}
