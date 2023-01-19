package org.marat.reckon.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        try (   var serverSocket = new ServerSocket(7777);
                var socket = serverSocket.accept();
                var outputStream = new DataOutputStream(socket.getOutputStream());
                var inputStream = new DataInputStream(socket.getInputStream());
                var scanner = new Scanner(System.in)
        )
        {
            var request = inputStream.readUTF();
            while (!"/q".equals(request)) {
                System.out.println("Client request: "+ request);
                var response = scanner.nextLine();
                outputStream.writeUTF(response);
                request = inputStream.readUTF();
            }
            System.out.println("Server closed connection by requset "+request);
        }
    }
}
