package site.yingjian.springmvc.demo.service;

import org.springframework.stereotype.Service;

@Service
public class Email {

    public void send() {
        System.out.println("send email...");
    }
}
