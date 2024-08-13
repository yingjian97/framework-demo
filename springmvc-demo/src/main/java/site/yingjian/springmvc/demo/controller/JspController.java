package site.yingjian.springmvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JspController {

    @RequestMapping("/")
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.jsp");
        return mv;
    }
}
