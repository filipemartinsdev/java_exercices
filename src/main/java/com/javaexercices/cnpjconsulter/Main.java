package com.javaexercices.cnpjconsulter;

import java.io.IOException;
import java.util.Scanner;

// teste
// 12279637000148
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scan = new Scanner(System.in);
        String input;

        CNPJClient client = new CNPJClient();

        while (true){
            System.out.println();
            System.out.println(">>> CONSULTA CNPJ <<<");
            System.out.print("> CNPJ (sem pontuação): ");
            input = scan.nextLine();
            System.out.println();

            String jsonResponse = client.get(input);
            if (jsonResponse==null){
                continue;
            }
            CNPJResponse response = CNPJClient.fromJson(jsonResponse);


            System.out.println("Bairro: "+response.bairro);
            System.out.println("Razão Social: "+response.razaoSocial);
            System.out.println("Nome fantasia: "+(!response.nomeFantasia.isBlank()? response.nomeFantasia : "null"));
            System.out.println("UF: "+response.uf);
            System.out.println("Municipio: "+response.municipio);
            System.out.println("Logradouro: "+response.logradouro);
            System.out.println("CEP: "+response.cep);
            System.out.println("CNAE Fiscal: "+response.cnaeFiscal);
            System.out.println("Email: "+response.email);

            System.out.print("Sócios: "+response.qsa[0].nome_socio);
            for (int i = 1; i<response.qsa.length; i++){
                System.out.print(", "+response.qsa[i].nome_socio);
            }

            System.out.println();
            System.out.println();
        }
    }
}
