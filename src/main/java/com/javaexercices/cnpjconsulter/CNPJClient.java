package com.javaexercices.cnpjconsulter;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CNPJClient {

     public String get(String CNPJ) {
        String webservice = "https://brasilapi.com.br/api/cnpj/v1/";

         HttpClient client = HttpClient.newBuilder()
                 .build();

         HttpRequest request = HttpRequest.newBuilder()
                 .uri(URI.create(webservice+CNPJ))
                 .GET()
                 .build();

         HttpResponse<String> response;
         try {
             response = client.send(request, HttpResponse.BodyHandlers.ofString());
             if (response.statusCode()==200) {
                 return response.body();
             } else {
                 System.out.println("[Invalid CNPJ]");
                 return null;
             }
         } catch (Exception e) {
//             throw new RuntimeException(e);
             System.out.println("[Invalid CNPJ]");
         }

         return null;
     }

     public static CNPJResponse fromJson(String json){
         Gson gson = new Gson();
         return gson.fromJson(json, CNPJResponse.class);
     }

     public static String toJson(CNPJResponse response){
         return new Gson().toJson(response);
     }
}
