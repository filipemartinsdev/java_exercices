package api;


import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.net.ServerSocket;
import java.net.Socket;

public class MyAPI {

    static List<User>userList = List.of(
            new User(1, "Filipe", "filipemartins@gmail.com"),
            new User(2, "Igor", "igor@hotmail.com"),
            new User(3, "victor", "hugo@yahoo.com")
    );

    static List<String>vipList = new ArrayList<>();


    public static void run() throws IOException {

        try(ServerSocket serverSocket = new ServerSocket(8080)){
            System.out.println("Server ON: http://localhost:8080");

            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("\n[Server] Nova conexão interceptada!");

                handleConnection(clientSocket);
            }
        }
    }


    private static void handleConnection(Socket clientSocket) throws IOException {
        System.out.println("[Server] Processando conexão...");

        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        String method;
        String path;

        String[] requestArr = reader.readLine().split(" ");
        method = requestArr[0];
        path = requestArr[1];

        System.out.println(method+" "+path);

        String response = routeRequest(method, path);

        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Length: "+response.length()+"\r\n");
        writer.write("Content-Type: application/json\r\n");
        writer.write("\r\n");
        writer.write(response+"\n");
        writer.flush();

        System.out.println("[Server] Resposta enviada!");
        System.out.println(response);
    }


    private static String routeRequest(String method, String path){

        if("GET".equals(method)){
            if("/users/".equals(path)){
                return getUserList();
            }

            if(path.startsWith("/users/")){
                String subStr = path.substring("/users/".length());

                int userId;

                try {
                    userId = Integer.parseInt(subStr);
                } catch (NumberFormatException e) {
                    return "{\"error\": \"user not found\"}";
                }
                User user = getUser(userId);
                if (user == null){
                    return "{\"error\": \"user not found\"}";
                }

                return user.toJson();
            }

            if("/viplist/".equals(path)){
                return getVipListJson();
            }
        }

        else if("POST".equals(method)){
            if(path.startsWith("/viplist/")){
                String name = path.substring("/viplist/".length());
                vipList.add(name);

                return "{\"status\":\"200\"}";
            }
            else {
                return "{\"error\":\"path not found\"}";
            }
        }

        return "{\"error\": \"method not supported\"}";
    }

    static User getUser(int id){
        for(User user:userList){
            if(user.getId() == id){
                return user;
            }
        }

        return null;
    }

    static String getVipListJson(){
        StringBuilder response = new StringBuilder();
        response.append("{");
        for (String user:vipList){
            response.append("\"").append(user).append("\", ");
        }
        return response.append("}").toString();
    }

    static String getUserList(){
        StringBuilder responseJson = new StringBuilder();
        responseJson.append("{");

        for (User user:userList){
            responseJson.append(user.toJson()).append(", ");
        }

        return responseJson.append("}").toString();
    }

    static class User {
        private int id;
        private String name;
        private String email;

        public User(int id, String name, String email){
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return this.name;
        }

        public String getEmail() {
            return this.email;
        }

        public String toJson(){
            return String.format(
                "{\"name\":\"%s\", \"email\":\"%s\"}",
                this.name, this.email
            );
        }

        public int getId() {
            return id;
        }
    }
}