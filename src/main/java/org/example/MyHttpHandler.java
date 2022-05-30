package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path=null;

        switch (httpExchange.getRequestMethod()) {

            case "GET":
                System.out.println("Server recieve GET.");
                URI uri = httpExchange.getRequestURI();
                path = new StringBuilder(uri.toString()).deleteCharAt(0).toString(); //.getRequestURI отдаёт "/something", нам надо просто "something"
                break;

            case "POST":
                System.out.println("Server recieve POST.");
                InputStream inputStream = httpExchange.getRequestBody();
                path = new BufferedReader(new InputStreamReader(inputStream)).readLine();    //читаем данные Body из Request-а
                break;
        }

        byte[] response = Files.readAllBytes(Paths.get(path));                      //file перегоняем в byte[] и передаём в .getResponseBody
        httpExchange.sendResponseHeaders(200, response.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response);
        System.out.println("Server send content of file: " + path);
        os.close();
    }
}