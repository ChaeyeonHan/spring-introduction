package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 구현체 생성
//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // Map에 <멤버아이디, 멤버>를 저장
    private static long sequence = 0L;  // 키값을 sequence가 생성해준다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);  // 멤버저장 -> 시퀀스값 한개 증가
        store.put(member.getId(), member);  // store에 저장한다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // store에서 가져온다. null일 경우를 위해 Optional.ofNullable로 감싸준다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))  // member의 name이 파라미터로 넘어온 name과 같은지 확인
                .findAny();  // 찾으면 반환해주기
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  // values가 멤버. 멤버를 반환한다
    }

    public void clearStore(){
        store.clear();
    }

}
