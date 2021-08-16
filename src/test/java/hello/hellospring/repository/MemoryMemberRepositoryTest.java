package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// 다른 데서 사용할 코드가 아니기 때문에 굳이 public 안해도 됨
@Repository
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 테스트 메소드가 종료될 때마다 실행되는 콜백 메소드
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Optional에서 값을 꺼낼 때 get()으로 꺼낼 수 있음
        // 그닥 좋은 방법은 아니지만 테스트 코드니...

//        System.out.println("result = " + (result == member));
        // 이렇게 문자열로 찍어보는 방법도 있고
//        Assertions.assertEquals(member, result);
        // junit에서 제공하는 Assertions 임포트
        // Assertions 이용하면 문자열은 안 뜨지만 테스트 결과가 초록불/빨간불

//        Assertions.assertThat(member).isEqualTo(result);
        // 처음에 이렇게 쓰고 Assertions에 커서 놓고 Alt+Enter 한 후
        // on-demand static import 하면 이후부터는 Assertions 생략하고 바로 아래 처럼 assertThat 사용 가능

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
