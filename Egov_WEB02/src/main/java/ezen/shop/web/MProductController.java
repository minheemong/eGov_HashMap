package ezen.shop.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ezen.shop.service.ProductService;

@Controller
public class MProductController {
	
	@Resource(name="ProductService") ProductService ps;
	
	@RequestMapping(value="/mobilemain.do")
	public String mobilemain( HttpServletRequest request, Model model) {
		
		HashMap<String, Object> paramMap1 = new HashMap<String, Object>();
		HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap1.put("ref_cursor", null);
		paramMap2.put("ref_cursor", null);
		ps.getBestList( paramMap1 );
		ps.getNewList( paramMap2 );
		
		ArrayList<HashMap<String, Object>> list1 
			= (ArrayList<HashMap<String, Object>>)paramMap1.get("ref_cursor");
		ArrayList<HashMap<String, Object>> list2 
			= (ArrayList<HashMap<String, Object>>)paramMap2.get("ref_cursor");
		
		model.addAttribute("bestList", list1);
		model.addAttribute("newList", list2);
		
		return "mobile/main";
	}
	
}
