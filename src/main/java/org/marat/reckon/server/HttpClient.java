package org.marat.reckon.server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;

import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpRequest.newBuilder;

public class HttpClient {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        var client = java.net.http.HttpClient.newBuilder()
                .version(java.net.http.HttpClient.Version.HTTP_1_1)
                .build();
        var request = newBuilder()
                .uri(URI.create("http://localhost:9000"))
                .header("content-type", "application/json")
                .POST(BodyPublishers.ofFile(Path.of(URI.create("file:/Users/marat/spring-wks/http-servlets-app/src/main/resources/data.json"))))
                .build();
        var response1 = client.sendAsync(request,HttpResponse.BodyHandlers.ofString());
        var response2 = client.sendAsync(request,HttpResponse.BodyHandlers.ofString());
        var response3 = client.sendAsync(request,HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.headers());
//        System.out.println(response.body());
        System.out.println(response3.get().body());
    }
}
