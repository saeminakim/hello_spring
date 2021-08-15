package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 테스트 시작하기 전에 실행
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 각 테스트 메소드가 종료될 때마다 실행되는 콜백 메소드
    public void afterEach() {
        memberRepository.clearStore();
    }


//    @Test
//    void join() {
//    }

    @Test // 테스트 메소드 이름은 한글로 적어도 무방하다!
    void 회원가입() {
        // given, when, then 으로 주석을 달면서 테스트 하면 좋음
        //given : 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("spring");

       //when : 이걸 실행했을 때
        Long saveId = memberService.join(member);

        //then : 결과가 이게 나와야 돼
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        // assertThrows(A, B)
        // B를 실행할 때 A 예외가 터져야 함
        // 예외를 잘 타는지 검증할 때 사용
//       assertThrows(IllegalStateException.class, () -> memberService.join(member2));

       // 예외 메시지를 검증하고 싶다면 위 메소드를 변수에 담아서 아래처럼 검증하면 됨
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


 /*       try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}