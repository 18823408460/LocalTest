package com.uurobot.baseframe.socket;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.uurobot.baseframe.R;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_socket);
                new Thread(new ReceiverRunnable()).start();
                new Thread(new SendRunnable()).start();
        }
        private class ReceiverRunnable implements Runnable {
                DatagramSocket datagramSocket = null;
                byte[] data = new byte[512];
                DatagramPacket dgp;

                public ReceiverRunnable() {
                        try {
                                datagramSocket = new DatagramSocket();
                        } catch (SocketException e) {
                                e.printStackTrace();
                                System.out.println("e===" + e);
                        }
                }

                @Override
                public void run() {
                        while (true) {
                                System.out.println("sever: wait receiver");
                                try {
                                        InetSocketAddress inetSocketAddress = new InetSocketAddress("192.168.12.254", 19999);
                                        dgp = new DatagramPacket(data, data.length, inetSocketAddress);
                                        datagramSocket.receive(dgp);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                        System.out.println("ee=" + e);
                                }
                                System.out.println("client:" + datagramSocket.getInetAddress() + "  " + datagramSocket.getPort());
                        }
                }
        }

        private  class SendRunnable implements Runnable {
                DatagramSocket datagramSocket;

                byte[] sendData = new byte[]{(byte) 0xAA, 0x55, 0x00, 0x4D, 0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, 0x50, 0x07, 0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, 0x2D, (byte) 0xB2, (byte) 0xC0, (byte) 0xA8,
                        0x0C, 0x0E, (byte) 0xC9, (byte) 0xF0, 0x00, 0x00, 0x01, 0x00, 0x00, 0x00, (byte) 0x8A, 0x43, 0x00, 0x01, 0x01, 0x00, 0x00, 0x51, 0x05, 0x01, 0x00, 0x1F, 0x00, 0x00,
                        0x51, 0x05, (byte) 0xFC, 0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, 0x50, 0x07, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
                DatagramPacket p;

                public SendRunnable() {
                        try {
                                datagramSocket = new DatagramSocket();
                                p = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("192.168.12.1"), 3000);
                        } catch (SocketException e) {
                                e.printStackTrace();
                                System.out.println("e1=" + e);
                        } catch (UnknownHostException e) {
                                e.printStackTrace();
                                System.out.println("e2=" + e);
                        }
                }

                @Override
                public void run() {
                        while (true) {
                                try {
                                        datagramSocket.send(p);
                                        Thread.sleep(2000);
                                        System.out.println("sendData=========" + InetAddress.getLocalHost() + "   " + p.getSocketAddress() + "   " + p.getPort() + "  " + p.getLength());
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
