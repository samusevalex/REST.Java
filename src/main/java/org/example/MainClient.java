package org.example;

import java.io.IOException;
import static org.example.Const.METHOD;

public class MainClient {
    public static void main(String[] args) throws IOException {
        switch (METHOD){
            case "get":
                new HttpClientGET().run();
                break;
            case "post":
                new HttpClientPOST().run();
                break;
        }
    }
}