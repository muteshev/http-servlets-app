package org.marat.reckon.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.Files.readAllBytes;

public class HttpServer {

    private int port;
    private ExecutorService pool;
    private boolean stopped;

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public HttpServer() {
    }

    public HttpServer(int port, int poolSize) {
        this.port = port;
        this.pool = Executors.newFixedThreadPool(poolSize);
    }

    public void run() {
        try (var serverSocket = new ServerSocket(port);
        ) {
            while(!stopped) {
                Socket socket = serverSocket.accept();
                System.out.println("Socket accepted");
                pool.submit(() -> processSocket(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try (socket;
             var inputStream = new DataInputStream(socket.getInputStream());
             var outputStream = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("Request: "+ new String(inputStream.readNBytes(455)));
            Thread.sleep(10000);
            var body = readAllBytes(Path.of(URI.create("file:/Users/marat/spring-wks/http-servlets-app/src/main/resources/data.html")));
            var headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %s
                    """.formatted(body.length).getBytes();
            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch (IOException | InterruptedException e) {
            /* TODO */
            e.printStackTrace();
        }
    }
}
