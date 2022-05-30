package org.example;

import java.io.*;
import java.net.*;

import static org.example.Const.*;

class HttpClientPOST {
    void run() throws IOException {
        URL url = new URL(REQUEST_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //Аналог curl
        httpURLConnection.setRequestMethod("POST");                                     //Каким методом будет запрашивать ресурс
        httpURLConnection.setDoOutput(true);

        OutputStream outputStream;
        try{                                                                            //Проверка на доступность сервера
            outputStream = httpURLConnection.getOutputStream();
        }catch (ConnectException e){
            System.err.println("Server " + REQUEST_URL +" isn't available");
            return;
        }

        PrintStream ps = new PrintStream(outputStream);                                 //Пишем в Body Request-а наши данные
        ps.println(TEXTFILE);

        System.out.println("Client send POST request as " + url.toString());
        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String str;                                                                     //Получаем с сервера данные и распечатываем в консоль
        while ((str = in.readLine()) != null)
            System.out.println(str);
    }
}