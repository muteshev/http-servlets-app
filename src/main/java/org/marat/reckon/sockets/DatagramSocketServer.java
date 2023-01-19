package org.marat.reckon.sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramSocketServer {
    public static void main(String[] args) throws IOException {
        try (var datagramSocket = new DatagramSocket(7777)) {
            byte[] buffer = new byte[512];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(packet);
            System.out.println(packet.getData().length);
        }
    }
}
