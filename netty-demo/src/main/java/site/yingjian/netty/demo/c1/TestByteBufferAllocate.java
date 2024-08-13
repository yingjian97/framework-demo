package site.yingjian.netty.demo.c1;

import java.nio.ByteBuffer;

public class TestByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());
        /*
        class java.nio.HeapByteBuffer    - java堆内存，读写效率低，受到GC的影响
        class java.nio.DirectByteBuffer  - 直接内存，读写效率高（少一次拷贝），不受GC的影响，分配内存的效率低
         */
    }
}
