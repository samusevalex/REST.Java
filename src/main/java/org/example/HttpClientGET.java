package org.example;

import java.io.*;
import java.net.*;

import static org.example.Const.*;

class HttpClientGET {
    void run() throws IOException {
        URL url = new URL(REQUEST_URL + TEXTFILE);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //Аналог curl
        httpURLConnection.setRequestMethod("GET");                                      //Каким методом будет запрашивать ресурс

        InputStream inputStream;
        try {                                                                           // Проверка на доступность сервера
            inputStream = httpURLConnection.getInputStream();
        }catch (ConnectException e){
            System.err.println("Server " + REQUEST_URL +" isn't available");
            return;
        }

        System.out.println("Client send GET request as " + url.toString());
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String str;                                                                      //Получаем с сервера данные и распечатываем в консоль
        while ((str = in.readLine()) != null)
            System.out.println(str);
    }
}