package org.marat.reckon.server;

public class HttpServerRunner {

    public static void main(String[] args) {
        new HttpServer(9000, 100).run();
    }
}
