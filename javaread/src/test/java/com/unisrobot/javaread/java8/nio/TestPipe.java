package com.unisrobot.javaread.java8.nio;

/**
 * Created by WEI on 2018/5/20.
 */

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 *   pipe : 在两个线程 之间 进行 数据的  单向传递
 */
public class TestPipe {

    @Test
    public void test() throws IOException {
        Pipe open = Pipe.open();

        // 数据发送端
        Pipe.SinkChannel sink = open.sink();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("通过管道发送数据".getBytes());
        buffer.flip();
        sink.write(buffer);
        buffer.clear();

        // 数据接收端
        Pipe.SourceChannel source = open.source();
        int read = source.read(buffer);
        String s = new String(buffer.array(), 0, read);
        System.out.println("s======="+s);

        sink.close();
        source.close();
    }
}
