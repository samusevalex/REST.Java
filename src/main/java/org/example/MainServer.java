package org.example;

import java.io.IOException;

public class MainServer {

    public static void main(String[] args) throws IOException {
        new HTTPServer().run();
    }
}