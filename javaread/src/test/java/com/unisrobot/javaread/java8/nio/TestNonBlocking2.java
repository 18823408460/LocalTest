package com.unisrobot.javaread.java8.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * Created by WEI on 2018/5/20.
 */

public class TestNonBlocking2 {
    @Test
    public void send() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
        datagramChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello".getBytes());
        buffer.flip();
        datagramChannel.send(buffer, new InetSocketAddress("127.0.0.1", 9999));

        datagramChannel.close();
    }

    @Test
    public void receiver() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress(9999));
        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    datagramChannel.receive(buffer);
                    buffer.flip();
                    String s = new String(buffer.array(), 0, buffer.limit());

                    System.out.println("s=== "+s);
                    buffer.clear();
                }
            }
            iterator.remove();

        }

    }


}
