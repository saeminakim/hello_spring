package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // HashMap 사용했지만 공유되는 변수의 경우 동시성 문제가 있을 수 있어
    // ConcurrentHashMap이나 AtomicLong 사용
    private static long sequence = 0L;
    // sequence 키캆을 자동으로 생성해주는 역할


    @Override
    public Member save(Member member) {
        member.setId(++sequence); // store에 넣기 전 id 값 하나 증가
        store.put(member.getId(), member); // store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // return store.get(id);
        // Optional이 없다면 위처럼 짜겠지만 null의 가능성이 있다면 Optional로 감싸줌
        // 그럼 클라이언트에서 뭔가 해줄 수 있음
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // store에서 루프 돌려서 멤버의 getName이 파라미터로 넘어온 name과 같은지 확인
        // 같은 경우에만 필터링 됨
        // findAny가 있어서 하나라도 같으면 나오고 null인 경우 Optional 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store는 Map인데 리턴타입이 List이기 때문에 
        // new ArrayList로 만들어줌
    }
}
