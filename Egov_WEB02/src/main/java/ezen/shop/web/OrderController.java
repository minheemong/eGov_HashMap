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

import ezen.shop.service.OrderService;

@Controller
public class OrderController {

	@Resource(name="OrderService")OrderService os;
	
	@RequestMapping("/orderInsert.do")
	public String orderInsert(HttpServletRequest request, Model model) {
		int oseq = 0;
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(loginUser==null) {
			return "member/login";
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", loginUser.get("ID"));
			paramMap.put("oseq", 0);
			
			os.insertOrder(paramMap); // 아이디와 oseq를 갖고 프로시저에 가서
			// 아이디로 카트검색
			// 검색내용으로 orders와 order_detail 테이블에 레코드 추가
			// 
			oseq = (int)paramMap.get("oseq");
			return "redirect:/orderList.do?oseq="+oseq;
		}
	}
	
	@RequestMapping(value = "/orderList.do")
	public String orderList(HttpServletRequest request, Model model) {
		int oseq = Integer.parseInt(request.getParameter("oseq"));
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(loginUser==null) {
			return "member/login";
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("oseq", oseq);
			os.listOrderByOseq(paramMap); 
			ArrayList<HashMap<String, Object>> list 
				= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			model.addAttribute("orderList", list);
			
			int totalPrice = 0;
			for(HashMap<String, Object> ovo : list) {
				totalPrice += Integer.parseInt(ovo.get("QUANTITY").toString())
						* Integer.parseInt(ovo.get("PRICE2").toString());
			}
			model.addAttribute("totalPrice", totalPrice);
		}
		return "mypage/orderList";
	}
	
	@RequestMapping(value = "/myPage.do")
	public String myPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if(loginUser==null) {
			return "member/login";
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("id", loginUser.get("ID"));
			os.listOrderByResult(paramMap); 
			// 주문번호 리스트 조회
			// 주문번호 별 주문내역 조회
			// 주문번호 별 대표상품을 별도의 리스트로 모아서 model에 저장
			ArrayList<HashMap<String, Object>> list 
				= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			model.addAttribute("orderList", list);
			model.addAttribute("title", "진행중인 주문내역");
		}
		return "mypage/mypage";
	}
}
