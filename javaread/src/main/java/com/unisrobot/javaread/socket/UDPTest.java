package com.unisrobot.javaread.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/6/22.
 */

public class UDPTest {
        public static void main(String[] args) {
                System.out.println("--------------start-------------");

                new Thread(new ReceiverRunnable()).start();
                new Thread(new SendRunnable()).start();
        }

        private static class ReceiverRunnable implements Runnable {
                DatagramSocket receiverSocket = null;
                byte[] data = new byte[512];
                DatagramPacket receiverPkg;

                @Override
                public void run() {
                        try {
                                receiverPkg = new DatagramPacket(data, data.length);
                                receiverSocket = new DatagramSocket(3000, InetAddress.getByName("192.168.12.254"));
                                System.out.println(" " + receiverPkg.getPort() + "  " + receiverPkg.getAddress());
                        } catch (SocketException e) {
                                e.printStackTrace();
                                System.out.println("e===" + e);
                        } catch (UnknownHostException e) {
                                e.printStackTrace();
                        }

                        while (true) {
                                try {
                                        System.out.println("sever: wait receiver ");
                                        receiverSocket.receive(receiverPkg);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                        System.out.println("ee=" + e);
                                }
                                System.out.println("sever receiver data:" + data[0]+ "  "+data[1] );
                        }
                }
        }

        private static class SendRunnable implements Runnable {
                DatagramSocket sendSocket;

                byte[] sendData = new byte[]{
                        (byte) 0xAA, 0x55, 0x00, 0x4D,
                        0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, 0x50, 0x07,
                        0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, 0x2D, (byte) 0xB2,
                        (byte) 0xC0, (byte) 0xA8, 0x0C, 0x0E,
                        (byte) 0xC9, (byte) 0xF0,
                        0x00, 0x00, 0x01, 0x00, 0x00, 0x00,
                        (byte) 0x8A, 0x43,
                        0x00, 0x01, 0x01, 0x00, 0x00,
                        0x51, 0x05, 0x01, 0x00, 0x1F, 0x00, 0x00,
                        0x51, 0x05, (byte) 0xFC,
                        0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99,0x50, 0x07,
                        0x00, 0x00, 0x00, 0x00, 0x00,0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
                };
                DatagramPacket sendPkg;

                @Override
                public void run() {
                        try {
                                sendSocket = new DatagramSocket();
                                sendPkg = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("192.168.12.1"), 3000);

                        } catch (SocketException e) {
                                e.printStackTrace();
                                System.out.println("e1=" + e);
                        } catch (UnknownHostException e) {
                                e.printStackTrace();
                                System.out.println("e2=" + e);
                        }

                        while (true) {
                                try {
                                        sendSocket.send(sendPkg);
                                        Thread.sleep(2000);
                                        System.out.println("send data===============");
                                } catch (IOException e) {
                                        e.printStackTrace();
                                        System.out.println("e3=" + e);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        System.out.println("e4=" + e);
                                }
                        }
                }
        }
}
