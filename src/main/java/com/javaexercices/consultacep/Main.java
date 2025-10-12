package com.javaexercices.consultacep;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);

        while (true){
            System.out.println("--- CONSULTA DE CEPs ---");
            System.out.print("\nCEP: ");
            String input = scan.nextLine();

            CEP CEP = parseCEP(consultarCEP(input));
            if (CEP!=null){
                System.out.println("CEP: "+CEP.getCep());
                System.out.println("Estado: "+CEP.getEstado());
                System.out.println("DDD: "+CEP.getDdd());
                System.out.println("Localidade: "+CEP.getLocalidade());
                System.out.println("Regiao: "+CEP.getRegiao());
                continue;
            }

            System.out.println("Invalid CEP\n");
        }
    }

    public static String consultarCEP(String CEP) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .timeout(Duration.ofSeconds(3))
                .uri(URI.create("https://viacep.com.br/ws/"+CEP+"/json"))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode()==200){
               return response.body();
        }

        return "{\"statusCode\":\""+response.statusCode()+"\"}";
    }

    public static CEP parseCEP(String json){
        Gson gson = new Gson();

        if (json.startsWith("{\"statusCode")){
            return null;
        }

        return gson.fromJson(json, CEP.class);
    }
}
