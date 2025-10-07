package consultacep;

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
            String cep = scan.nextLine();

            System.out.println(consultarCEP(cep));
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

        client.close();
        return "{\"statusCode\":\""+response.statusCode()+"\"}";
    }
}
