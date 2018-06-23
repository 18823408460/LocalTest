package com.unisrobot.javaread.socket;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/22.
 *
 * https://blog.csdn.net/yeqishi/article/details/7091617
 *
 */

public class UDPTest {
        /**
         * 0x01/2/3/4/5/6
         * 1:光照传感器;2:温湿度传感器;3:人体传感器;4:温度传感器;5:超声波传感器;6:按键传感器
         */
        static byte devType = 0x05;
        static byte[] csb = {0x2D, (byte) 0xB2};
        static byte[] wenshidu = {0x2D, (byte) 0xd8};
        static byte[] wendu = {(byte) 0xC2, (byte) 0x8a};
        static byte[] guangzhao = {(byte) 0xab, (byte) 0x20};
        static byte[] anjian = {(byte) 0xa8, (byte) 0xd7};
        static byte[] renti = {(byte) 0xba, (byte) 0x02};

        static byte devMacH = (byte) 0x2D;
        static byte devMacL = (byte) 0xB2;
        static byte[] sendData = new byte[]{
                (byte) 0xAA, 0x55, 0x00, 0x4D,
                0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, 0x50, 0x07,
                0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, devMacH, devMacL,
                (byte) 0xC0, (byte) 0xA8, 0x0C, 0x0E,
                (byte) 0xC9, (byte) 0xF0,
                0x00, 0x00, 0x01, 0x00, 0x00, 0x00,
                (byte) 0x8A, 0x43,
                0x00, 0x01, 0x01, 0x00, 0x00,
                0x51, devType, 0x01, 0x00, 0x1F, 0x00, 0x00,
                0x51, 0x05, (byte) 0xFC,
                0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, 0x50, 0x07,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };

        static byte[] getData = new byte[]{(byte) 0xAA, 0x55, 0x00, 0x2E, 0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, 0x50, 0x07, 0x00
                , 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, devMacH, devMacL, (byte) 0xC0, (byte) 0xA8, 0x0C, 0x0E, (byte) 0xC9, (byte) 0xF0, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00
                , (byte) 0x8A, 0x43, 0x00, 0x01, 0x01, 0x00, 0x00, 0x51, devType, 0x02, 0x00, 0x00, 0x00, 0x00};

        public static void main(String[] args) {
                System.out.println("--------------start-------------");

                new Thread(new ReceiverRunnable()).start();
//                new Thread(new SendRunnable()).start();
        }

        private static class ReceiverRunnable implements Runnable {
                DatagramSocket receiverSocket = null;
                byte[] data = new byte[112];
                DatagramPacket receiverPkg;
                DatagramPacket sendPkg;

                @Override
                public void run() {
                        try {
                                receiverSocket = new DatagramSocket(19999/*, InetAddress.getByName("192.168.12.254")*/);

                                sendPkg = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("192.168.12.1"), 3000);
                                 receiverSocket.send(sendPkg);
                        } catch (SocketException e) {
                                e.printStackTrace();
                                System.out.println("e===" + e);
                        } catch (UnknownHostException e) {
                                e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                        receiverPkg = new DatagramPacket(data, data.length);
                        while (true) {
                                try {

                                        System.out.println("sever: wait receiver ");
                                        receiverSocket.receive(receiverPkg);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                        System.out.println("ee=" + e);
                                }
                                parseData(receiverPkg.getData());
                        }
                }
        }

        private static void parseData(byte[] data) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
                int available = byteArrayInputStream.available();
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                while (true) {
                        try {
                                int b = dataInputStream.readByte() & 0xff;//这里必须要&0xff，否则会使一个负数，同时要用int 接受
                                System.out.println(b);
                                if (b == 0xAA) {
                                        int aByte = dataInputStream.readByte() & 0xff;
                                        if (aByte == 0x55) {
                                                int aByteH = dataInputStream.readByte() & 0xff;
                                                int aByteL = dataInputStream.readByte() & 0xff;
                                                int len = aByteH * 256 + aByteL;
                                                byte[] bytes = new byte[len];
                                                dataInputStream.readFully(bytes);
                                                System.out.println("len========== " + bytesToHexString(bytes, len));
                                        }
                                }
                                break;
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }


        public static String bytesToHexString(byte[] src, int len) {
                StringBuilder stringBuilder = new StringBuilder("");
                if (src == null || src.length <= 0) {
                        return null;
                }
                for (int i = 0; i < len; i++) {
                        int v = src[i] & 0xFF;
                        String hv = Integer.toHexString(v);
                        if (hv.length() < 2) {
                                stringBuilder.append("0" + hv + ", ");
                        } else {
                                stringBuilder.append(hv + ",  ");
                        }

                }
                return stringBuilder.toString();
        }


        private static class SendRunnable implements Runnable {
                DatagramSocket sendSocket;
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
