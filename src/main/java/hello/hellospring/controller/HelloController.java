package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
//        스프링이 model이라는걸 만들어서 넣어줌
        model.addAttribute("data", "hello!!");
        return "hello";
//        hello.html 파일을 리턴해줌
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // html <body>에 해당하는 부분에 아래 데이터를 직접 넣어주겠다는 뜻
    // 실제로 html을 그려준다는건 아님. 내용만 들어감
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
        // 이 문자값을 http 응답에 넣어서 보내줌
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // 객체가 오면 JSON 방식으로 데이터를 만들어서 http 응답에 반환하는게 기본 정책
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
