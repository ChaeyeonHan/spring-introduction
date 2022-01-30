package hello.hellospring.controller;

public class MemberForm {

    private String name;  // createMemberForm의 form의 name과 name이 매칭된다.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
