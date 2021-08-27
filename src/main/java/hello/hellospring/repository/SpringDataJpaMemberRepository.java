package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL select m from Member m where m.name = ?
    // findByNameAndId 이런 식으로 인터페이스 이름을 형식에 맞춰주면 됨
    @Override
    Optional<Member> findByName(String name);
}
