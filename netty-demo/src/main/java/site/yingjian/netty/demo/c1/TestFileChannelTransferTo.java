package site.yingjian.netty.demo.c1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannelTransferTo {
    static final String path = "C:\\Users\\admin\\IdeaProjects\\framework-demo\\netty-demo\\src\\main\\resources\\";

    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream(path + "data.txt").getChannel();
             FileChannel to = new FileOutputStream(path + "to.txt").getChannel()) {
            long size = from.size();
            for (long left = size; left > 0; ) {
                // 限制2G
                left -= from.transferTo((size - left), left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}