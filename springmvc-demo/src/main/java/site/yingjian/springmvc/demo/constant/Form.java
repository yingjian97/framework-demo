package site.yingjian.springmvc.demo.constant;


import site.yingjian.springmvc.demo.service.Email;
import site.yingjian.springmvc.demo.util.SpringContextUtil;

public enum Form {

    TRANS {
        @Override
        public void write() {
            Email email = SpringContextUtil.getBean(Email.class);
            email.send();
            System.out.println("write...");
        }
    },
    ;


    public abstract void write();

}
