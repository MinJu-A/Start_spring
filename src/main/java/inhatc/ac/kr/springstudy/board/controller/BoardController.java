package inhatc.ac.kr.springstudy.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 이 Springboot가 이 클래스를 Controller로 인식하게 해주는 어노테이션
@Controller

//@RestController
//내가 원하는 결과물만 바로 찍어낼 수 있게 해주는 어노테이션
public class BoardController {
	
//	요구가 들어왔을 때 어떻게 설정할건지 정해주는 어노테이션
//	localhost:45101/으로 접속 시 어떻게 설정할지 정해준다고 생각하면 된다.
//	얘는 웹 페이지를 매핑해주는 어노테이션이기 때문에 .html이라고 되어있는 친구를 찾아서 데려오는 일을 한다. 
	@RequestMapping("/")
	
// 접속 시 Hello World를 출력하는 메소드 선언
//	스프링부트는 이렇게 변경을 했을 때 서버를 끄고 켜지 않아도 돼요
	public String hello() {
		return "index";
//		restcontroller로 햇을 때는 retrun "출력하고 싶은 문구";
	}

}