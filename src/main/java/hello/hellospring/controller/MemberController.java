package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    // @Autowired 어노테이션을 쓰면 생성자 매개변수에 있는 memberService를
    // Spring Container에 있는 memberService와 연결 시켜줌
     public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
