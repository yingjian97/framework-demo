package site.yingjian.netty.demo.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static site.yingjian.netty.demo.c1.ByteBufferUtil.debugRead;

@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false); // 设置非阻塞模式
        // SelectionKey事件类型：accept，connect，read，write
        SelectionKey sscKey = ssc.register(selector, 0, null); // Channel关联Selector
        sscKey.interestOps(SelectionKey.OP_ACCEPT); // 关注accept事件
        log.debug("register key: {}", sscKey);
        ssc.bind(new InetSocketAddress(8080));

        while (true) {
            // select()方法：若没有事件则阻塞线程，若有事件则恢复线程运行
            selector.select();
            // 处理事件，selectedKeys()包含所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                log.debug("key: {}", key);
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel sc = channel.accept();
                log.debug("{}", sc);
            }
        }
    }
}
