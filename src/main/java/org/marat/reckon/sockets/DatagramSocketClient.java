package org.marat.reckon.sockets;

import java.io.IOException;
import java.net.*;

public class DatagramSocketClient {

    public static void main(String[] args) throws IOException {
        var inetAddress = InetAddress.getByName("localhost");
        try (var datagramSocket = new DatagramSocket()) {
            var bytes = "UDP packet data".getBytes();
            var packet = new DatagramPacket(bytes, bytes.length, inetAddress, 7777);
            datagramSocket.send(packet);
        }
    }
}
