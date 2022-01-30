package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;
    // 스프링 컨테이너에 등록을 하고 사용한다

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){

        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";  // 회원가입이 끝나면 홈화면으로 다시 돌아가기기
    }

    @GetMapping("/members")
    public  String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
