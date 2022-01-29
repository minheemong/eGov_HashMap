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
public class ProductController {

	@Resource(name="ProductService") ProductService ps;
	// ps 는 ProductService 인터페이스의 레퍼런스 변수이고, 그 변수에 저장되는 인스턴스는 @Service 어너테이션이 있는
	// ProductServiceimpl 클래스의 인스턴스가 @Resource에 의해서 자동주입됩니다
	// @Autowired 는 클래스 이름으로 자동주입할 클래스를 검색하지만,  @Resource 는 어너테이션  value  에 있는 이름로 검색합니다
	
	@RequestMapping(value="/main.do")
	public String main( HttpServletRequest request, Model model) {
		
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
		
		return "main";
	}
	
	
	@RequestMapping(value="/catagory.do")
	public String catagory(Model model, HttpServletRequest request) {
		String kind = request.getParameter("kind");
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("ref_cursor", null);
		paramMap.put("kind", kind);
		ps.getKindList(paramMap);
		ArrayList<HashMap<String, Object>> list 
			= (ArrayList<HashMap<String, Object>>)paramMap.get("ref_cursor");
		model.addAttribute("productKindList", 	list  );
		return "product/productKind";  
	}
}












