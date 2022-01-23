package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 하나 끝날때마다 repository를 지워주는 메소드 => 지우지 않고 전체를 돌리면 이전에 저장된 멤버들이 지워지지않아 문제가 생긴다.
    // 테스트는 서로 순서가 상관없어야함(의존관계가 없어야한다.)
    @AfterEach  //동작이 끝날때마다 실행되는 콜백메소드
    public void afterEach(){
        repository.clearStore();
    }

   @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");  // 멤버이름 spring이라고 설정

        repository.save(member);  // repository에 저장

        Member result = repository.findById(member.getId()).get();  // 반환값이 optional. optional에서 값을 꺼낼때 get사용
        //System.out.println("result = " + (result == member));  // 저장한 값과 db에서 꺼내온 값이 동일한지 확인
        // Assertions.assertEquals(member, result);  // expected, actual 순으로 (성공)
        // Assertions.assertEquals(member, null);  // 실패

        assertThat(member).isEqualTo(result);  // 더 편리한 문법제공. member가 result랑 동일한지
    }

    @Test
    public void findByName(){
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
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);  // 갯수가 2개 나와야 한다
    }

}
