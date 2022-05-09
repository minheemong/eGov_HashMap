package ezen.shop.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ezen.shop.service.CartService;

@Controller
public class CartController {

	@Resource(name="CartService") CartService cs;
	
	@RequestMapping(value="/cartInsert.do")
	public String cartInsert( HttpServletRequest request, Model model) {
		
		int pseq = Integer.parseInt( request.getParameter("pseq") );
		int quantity = Integer.parseInt( request.getParameter("quantity") );
		
		// 세션값을 얻어서 로그인체크
		// paramMap 에 해당 내용을 put 하고 insertCart 메서드를 호출 & 제작 하여 카드테이블에 해당 레코드를 추가합니다.
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		
		if( loginUser == null ) {
			return "member/login";
		
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", loginUser.get("ID") );
			paramMap.put("pseq", pseq);
			paramMap.put("quantity" , quantity);
			
			cs.insertCart( paramMap );
		}
		return "redirect:/cartList.do";
	}
	
	
	@RequestMapping(value="/cartList.do")
	public String cartList( HttpServletRequest request, Model model ) {		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = (HashMap<String, Object>)session.getAttribute("loginUser");
		if( loginUser == null ) {
			return "member/login";
		}else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", loginUser.get("ID") );
			paramMap.put("ref_cursor", null);
			cs.listCart(paramMap);
			ArrayList<HashMap<String, Object>> list 
				= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
			model.addAttribute("cartList", list);
			
			int totalPrice = 0;
			for( HashMap<String, Object> cart : list ) {
				totalPrice += Integer.parseInt( cart.get("QUANTITY").toString() )
									* Integer.parseInt( cart.get("PRICE2").toString() ); 
			}
			model.addAttribute("totalPrice", totalPrice);
		}
		return "mypage/cartList";
	}
	
	
	@RequestMapping(value="/cartDelete.do")
	public String cartDelete( HttpServletRequest request ) {
		String[] cseqArr = request.getParameterValues("cseq");
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		for( String cseq : cseqArr) {
			paramMap.put("cseq", cseq);
			cs.deleteCart(paramMap);
		}
		return "redirect:/cartList.do";
	}
}






