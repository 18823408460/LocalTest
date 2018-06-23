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
        /**
         * 0x01/2/3/4/5/6
         1:光照传感器;2:温湿度传感器;3:人体传感器;4:温度传感器;5:超声波传感器;6:按键传感器
         */
        static byte devType = 0x03;
        static byte[] csb = {0x2D, (byte) 0xB2};
        static byte[] wenshidu = {0x2D, (byte) 0xd8};
        static byte[] wendu = {(byte) 0xC2, (byte) 0x8a};
        static byte[] guangzhao = {(byte) 0xab, (byte) 0x20};
        static byte[] anjian = {(byte) 0xa8, (byte) 0xd7};
        static byte[] renti = {(byte) 0xba , (byte) 0x02};

        static byte devMacH = (byte) 0xba;
        static byte devMacL = (byte) 0x02;
        static byte[] sendData = new byte[]{
                (byte) 0xAA, 0x55, 0x00, 0x4D,
                0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, 0x50, 0x07,
                0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99, devMacH,  devMacL,
                (byte) 0xC0, (byte) 0xA8, 0x0C, 0x0E,
                (byte) 0xC9, (byte) 0xF0,
                0x00, 0x00, 0x01, 0x00, 0x00, 0x00,
                (byte) 0x8A, 0x43,
                0x00, 0x01, 0x01, 0x00, 0x00,
                0x51, devType, 0x01, 0x00, 0x1F, 0x00, 0x00,
                0x51, 0x05, (byte) 0xFC,
                0x00, 0x12, 0x4B, 0x00, 0x19, (byte) 0x99,0x50, 0x07,
                0x00, 0x00, 0x00, 0x00, 0x00,0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
        };

        static byte[] getData = new byte[]{(byte) 0xAA, 0x55, 0x00 ,0x2E ,0x00 ,0x12 ,0x4B ,0x00 ,0x19 , (byte) 0x99,0x50 ,0x07 ,0x00
                ,0x12 ,0x4B ,0x00 ,0x19 , (byte) 0x99,devMacH ,devMacL, (byte) 0xC0, (byte) 0xA8,0x0C ,0x0E , (byte) 0xC9, (byte) 0xF0,0x00 ,0x00 ,0x01 ,0x00 ,0x00 ,0x00
                , (byte) 0x8A,0x43 ,0x00 ,0x01 ,0x01 ,0x00 ,0x00 ,0x51 ,devType ,0x02 ,0x00 ,0x00 ,0x00 ,0x00 };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_socket);
                new Thread(new ReceiverRunnable()).start();
        }
        private class ReceiverRunnable implements Runnable {
                DatagramSocket receiverSocket = null;
                byte[] data = new byte[512];
                DatagramPacket receiverPkg;
                DatagramPacket sendPkg;
                int count = 0 ;
                @Override
                public void run() {
                        try {
                                receiverPkg = new DatagramPacket(data, data.length,InetAddress.getByName("192.168.12.254"),19999);
                                receiverSocket = new DatagramSocket( );
                                sendPkg = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("192.168.12.1"), 3000);
                                receiverSocket.send(sendPkg);
                                receiverSocket.send(sendPkg);
                        } catch (SocketException e) {
                                e.printStackTrace();
                                System.out.println("e===" + e);
                        } catch (UnknownHostException e) {
                                e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        }

                        while (true) {
                                if (count == 0){
                                        count++ ;
                                        try {
                                                sendPkg = new DatagramPacket(getData, getData.length, InetAddress.getByName("192.168.12.1"), 3000);
                                                receiverSocket.send(sendPkg);
                                                receiverSocket.send(sendPkg);
                                        } catch (UnknownHostException e) {
                                                e.printStackTrace();
                                        } catch (IOException e) {
                                                e.printStackTrace();
                                        }
                                }
                                try {

                                        System.out.println("sever: wait receiver ");
                                        receiverSocket.receive(receiverPkg);
                                } catch (IOException e) {
                                        e.printStackTrace();
                                        System.out.println("ee=" + e);
                                }
                                System.out.println("sever receiver data:" + bytesToHexString(receiverPkg.getData(),100) );

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

        public static String bytesToHexString(byte[] src,int len){
                StringBuilder stringBuilder = new StringBuilder("");
                if (src == null || src.length <= 0) {
                        return null;
                }
                for (int i = 0; i < len; i++) {
                        int v = src[i] & 0xFF;
                        String hv = Integer.toHexString(v);
                        if (hv.length() < 2) {
                                stringBuilder.append("0"+hv+", ");
                        }else{
                                stringBuilder.append(hv+",  ");
                        }

                }
                return stringBuilder.toString();
        }
}
