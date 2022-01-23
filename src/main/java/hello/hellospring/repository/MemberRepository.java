package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);  // 회원저장
    Optional<Member> findById(Long id);  // 아이디로 회원찾기
    Optional<Member> findByName(String name);  //optional -> 아이디나 이름으로 찾을때 없을경우 null을 optional로 감싸서 반환한다.
    List<Member> findAll();  // 저장된 모든 회원 리스트 조회하기
    // 4가지 기능
}
