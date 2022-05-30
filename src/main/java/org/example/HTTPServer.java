package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

class HTTPServer {
    void run() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHttpHandler());     //привязываем URI к севлету
        //server.setExecutor(null);                            //Каждый request это новый task, который передаётся Executor-у. Многопоточность.
        server.start();
        System.out.println("Server started.");
    }
}