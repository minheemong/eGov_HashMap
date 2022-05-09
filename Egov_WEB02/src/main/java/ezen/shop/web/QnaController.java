package ezen.shop.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ezen.shop.service.QnaService;

@Controller
public class QnaController {

	@Resource(name="QnaService") QnaService qs;
	
	@RequestMapping(value="/qnaList.do")
	public String qna_list(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			return "member/login";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", loginUser.get("ID") );
			paramMap.put("ref_curser", null);
			qs.listQna( paramMap );
			
			ArrayList<HashMap<String, Object>> list 
			= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			model.addAttribute("qnaList", list);			
		}
		return "qna/qnaList";
	}
	
	
	
	@RequestMapping(value="/qnaView.do")
	public String qna_view(Model model, HttpServletRequest request,
			@RequestParam("qseq") int qseq) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			return "member/login";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("qseq", qseq );
			paramMap.put("ref_curser", null);
			qs.getQna( paramMap );
			
			ArrayList<HashMap<String, Object>> list 
			= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			model.addAttribute("qnaVO", list.get(0) );			
		}
		
		
		return "qna/qnaView";
	}
	
	
	
	@RequestMapping(value="/qnaWriteForm.do")
	public String qna_writre_form( HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) return "member/login";
		
	    return "qna/qnaWrite";
	}
	
	
	@RequestMapping(value="/qnaWrite.do")
	public String qna_write( HttpServletRequest request,
			@RequestParam("subject") String subject, @RequestParam("content") String content) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) return "member/login";
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", loginUser.get("ID") );
		paramMap.put("subject", subject );
		paramMap.put("content", content);
		qs.insertQna( paramMap );
		
		return "redirect:/qnaList.do";
	}
}





