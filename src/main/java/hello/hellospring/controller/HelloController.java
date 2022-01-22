package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")  // '/hello' 라고 들어오면 이 메소드를 호출한다.
    public String hello(Model model){
        model.addAttribute("data", "hello!!");  // 데이터를 hello라고 넘겨준다.
        return "hello";  // resources-template에 있는 hello로 연결된다.
    }

    // #1번 방법
    @GetMapping("hello-mvc")  //외부에서 파라미터로 받아오기
    public String helloMvc(@RequestParam("name") String name, Model model){  // 디폴트가 reqired=True이기 때문에 값을 넘겨야한다.
        model.addAttribute("name", name);
        return "hello-template";  // hello-template.html로 이동한다.
    }

    // #2번 방법(API방식)
    // 돌려서 소스보기로 확인해보면, html태그는 하나도 없고 "hello spring"만 내려간다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();  // 객체 생성
        hello.setName(name);
        return hello;

    }

    // getter, setter 생성 단축기 : Alt + Insert
    // static class: class 안에서 클래스 쓰기
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}