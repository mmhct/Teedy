package com.sismics.util.io;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.Test;

public class MyTest {

    @Test
    public void testStreamReadAndClose() throws Exception {
        // 1. 模拟输入数据
        String testData = "Line1\nLine2\n";
        InputStream inputStream = new ByteArrayInputStream(testData.getBytes());

        // 2. 创建并启动线程
        InputStreamReaderThread thread = new InputStreamReaderThread(inputStream, "TestStream");
        thread.start();
        thread.join(); // 等待线程结束

        // 3. 间接验证资源关闭：读取后的流应不可再读
        // （若流未关闭，available() 会返回剩余字节数）
        assertEquals("Stream should be closed", 0, inputStream.available());
    }
}