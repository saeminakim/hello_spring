package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName());
        // 매개변수로 들어온 멤버를 repository에서 이름으로 검색하여 result라는 변수에 담아줌
        // Optional로 리턴되기 때문에 ifPresent 사용해서 예외처리 가능
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 또는 아래처럼 변수에 담지않고 바로 ifPresent 메소드 호출 가능
        // -> 어차피 optional 리턴되니까 
//        memberRepository.findByName(member.getName())
//            .ifPresent(m -> {
//                throw new IllegalStateException("이미 존재하는 회원입니다.");
//            });
        // 이런 내용은 메소드로 따로 뽑는게 좋음!

        // 중복 회원 검증
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
