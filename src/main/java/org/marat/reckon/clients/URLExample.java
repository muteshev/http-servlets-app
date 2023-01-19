package org.marat.reckon.clients;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;

public class URLExample {
    public static void main(String[] args) throws IOException {
        var url = new URL("file:/Users/marat/spring-wks/http-servlets-app/src/main/java/org/marat/reckon/sockets/SocketClient.java");
        var urlConnection = url.openConnection();
        System.out.println(new String(urlConnection.getInputStream().readAllBytes()));
        urlConnection.getInputStream().close();
        checkGoogle();
    }

    private static void checkGoogle() throws IOException {
        var url = new URL("http://www.google.com");
        var urlConnection = url.openConnection();
        var headerFields = urlConnection.getHeaderFields();
        headerFields.keySet().forEach(v-> System.out.println(v));
        try {
            urlConnection.setDoOutput(true);
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
//        try (var outputStream = new DataOutputStream(urlConnection.getOutputStream())) {
//            outputStream.writeUTF("output stream data");
//        }
        System.out.println();
    }
}
/*
need to check why
            urlConnection.setDoOutput(true);
throws exception "Already connected"
 */