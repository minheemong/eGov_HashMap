package ezen.shop.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ezen.shop.service.MemberService;

@Controller
public class MMemberController {

	@Resource(name="MemberService") MemberService ms;
	
	@RequestMapping(value="/mloginForm.do")
	public String loginForm() {
		return "mobile/member/login";
	}
}
