package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    // => DI(의존성 주입)라고 부른다!
    // memberService의 입장에서는 직접 new 하지않고, 외부에서 넣어준다
    // => 이런걸 dependency injection(의존성 주입)이라고 부른다.
    @BeforeEach // 동작하기전에 실행
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    // 테스트 이름은 한글로 작성해도 상관없음.
    // 1.given : 주어진 상황, 2.when : 실행했을때, 3.then : 결과 , 문법을 사용하여 작성하는 연습하기
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);  // 저장된 아이디가 반환됨

        //then - 검증
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    // 테스트는 정상과 예외 둘 다 존재
    @Test
    public void 중복_회원_예외(){
        //given : 이름 동일한 회원 만들어서
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        // 1.try-catch를 이용해서(잘 사용X) 2. assertThrows 이용
//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();  // 예외가 발생해야하는데 그냥 넘어갔으므로 실패
//        } catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 멤버2를 join하면 예외가 발생해야한다

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}