package site.yingjian.netty.demo.c1;

import java.nio.ByteBuffer;

import static site.yingjian.netty.demo.c1.ByteBufferUtil.debugAll;

public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61); // 'a'
        debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64}); // b c d
        debugAll(buffer);
        buffer.flip();
        System.out.println(buffer.get());
        debugAll(buffer);
        buffer.compact();
        debugAll(buffer);
        buffer.put(new byte[]{0x65, 0x6f});
        debugAll(buffer);
    }
}
