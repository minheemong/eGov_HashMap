package ezen.shop.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ezen.shop.service.MemberService;

@Controller
public class MemberController {

	@Resource(name="MemberService") MemberService ms;
	
	@RequestMapping(value="/loginForm.do")
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value="/login.do")
	public String login( Model model, HttpServletRequest request ) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pwd"); 
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "ref_cursor", null );
		paramMap.put("id", id);
		ms.getMember(paramMap);	 // 조회 
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		if(list.size() == 0) {  // 입력한 아이디 없다면
			model.addAttribute("message" , "아이디가 없습니다");
			return "member/login";
		}
		HashMap<String, Object> resultMap = list.get(0);  // 있다면  리스트에서 첫번째 인원정보 추출해서  해시맵에 저장
		if(resultMap.get("PWD")==null) {
			model.addAttribute("message" , "비밀번호가 없습니다");
			return "member/login";
		}else if( pw.equals( (String)resultMap.get("PWD") ) ) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", resultMap);
			return "redirect:/main.do";
		}else if( !pw.equals( (String)resultMap.get("PWD") ) ) {
			model.addAttribute("message" , "비밀번호가 맞지 않습니다");
			return "member/login";
		}else {
			model.addAttribute("message" , "알 수 없는 이유로 로그인되지 않습니다");
			return "member/login";
		}
	}
	
	
	@RequestMapping(value="/contract.do")
	public String contract() {
		return "member/contract";
	}
	
	@RequestMapping(value="/joinForm.do", method=RequestMethod.POST)
	public String join_form() {
		return "member/joinForm";
	}
	
	@RequestMapping("/idCheckForm.do")
	public String id_check_form( Model model, HttpServletRequest request ) {
		String id = request.getParameter("id");
		 
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "ref_cursor", null );
		paramMap.put("id", id);
		ms.getMember(paramMap);	 // 조회 
		
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		if(list.size() == 0) model.addAttribute("result", -1); // 사용 가능
		else model.addAttribute("result", 1); // 이미 사용중
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	
	@RequestMapping(value="/findZipNum.do")
	public String find_zip( HttpServletRequest request , Model model) {
		String dong=request.getParameter("dong");
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(dong != null && dong.trim().equals("")==false){			// trim : 공백제거
			paramMap.put( "ref_cursor", null );
			paramMap.put("dong", dong);
			ms.selectAddressByDong(paramMap);
			ArrayList< HashMap<String,Object> > list 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			System.out.println(list.size() + dong);
			model.addAttribute("addressList" , list);
		}
		return "member/findZipNum";
	}
	
	
	@RequestMapping(value = "/join.do", method=RequestMethod.POST)
	public String join(Model model, HttpServletRequest request) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("id", request.getParameter("id") );
		paramMap.put("pwd" , request.getParameter("pwd"));
		paramMap.put( "name" ,  request.getParameter("name"));
		paramMap.put( "phone" , request.getParameter("phone"));
		paramMap.put( "email" , request.getParameter("email"));
		paramMap.put( "zip_num" , request.getParameter("zip_num"));
		paramMap.put( "address" , request.getParameter("addr1") + " " + request.getParameter("addr2"));
		
		ms.insertMember(paramMap);
		
		model.addAttribute("message", "회원가입이 완료되었어요. 로그인하세요");
		return "member/login";
	}
	
	@RequestMapping(value = "/memberEditForm.do")
	public String memberEditForm( HttpServletRequest request, Model model ) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		String addr = (String)loginUser.get("ADDRESS");
		
		String addr1="";
		String addr2="";
		if( addr != null) {
			int k1 = addr.indexOf(" "); // indexOf : 문자열 앞에서부터 " " 위치 반환
			int k2 = addr.indexOf(" ", k1+1);
			int k3 = addr.indexOf(" ", k2+1);
			addr1 = addr.substring(0, k3); // substring : 0부터 k3까지 반환
			addr2 = addr.substring(k3+1); // k3+1부터 반환
		}
		model.addAttribute("addr1", addr1);
		model.addAttribute("addr2", addr2);
		
		return "member/memberUpdateForm";
	}
	
	
	@RequestMapping(value="/memberUpdate.do", method=RequestMethod.POST)
	public String memberEdit(Model model, HttpServletRequest request) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put( "ID", request.getParameter("id") );
		paramMap.put( "PWD" , request.getParameter("pwd"));
		paramMap.put( "NAME" ,  request.getParameter("name"));
		paramMap.put( "PHONE" , request.getParameter("phone"));
		paramMap.put( "EMAIL" , request.getParameter("email"));
		paramMap.put("ZIP_NUM", request.getParameter("zip_num"));
		paramMap.put( "ADDRESS" , request.getParameter("addr1") + " " + request.getParameter("addr2"));
				
		ms.updateMember(paramMap);
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", paramMap);
		return "redirect:/main.do";
	}
	
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/main.do";
	}
}















