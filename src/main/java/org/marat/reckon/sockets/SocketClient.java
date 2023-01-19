package org.marat.reckon.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        var inetAddress = Inet4Address.getByName("localhost");
        for (InetAddress address : InetAddress.getAllByName("127.0.0.1")) {
            System.out.println(address.getHostName());
        }
        try (
                var socket = new Socket(inetAddress, 7777);
                var outputStream = new DataOutputStream(socket.getOutputStream());
                var inputStream = new DataInputStream(socket.getInputStream());
                var scanner = new Scanner(System.in)
            ) {
                while (scanner.hasNextLine()) {
                    var request = scanner.nextLine();
                    outputStream.writeUTF(request);
                    var bytes = inputStream.readUTF();
                    System.out.println("Response from Server: "+bytes);
                    System.out.println(bytes.length());
                }
            } catch(EOFException eof) {
            System.out.println("connection closed");
        } catch (ConnectException connectException) {
            System.out.println("Server is down (start server first)");
        }
        var s = new String("Done");
        System.out.println(s);
    }
}