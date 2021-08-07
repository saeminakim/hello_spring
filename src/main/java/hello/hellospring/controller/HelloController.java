package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
//        스프링이 model이라는걸 만들어서 넣어줌
        model.addAttribute("data", "hello!!");
        return "hello";
//        hello.html 파일을 리턴해줌
    }
}
