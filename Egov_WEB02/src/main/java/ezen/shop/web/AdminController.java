package ezen.shop.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ezen.shop.dto.Paging;
import ezen.shop.service.AdminService;
import ezen.shop.service.ProductService;

@Controller
public class AdminController {

	@Resource(name="AdminService") AdminService as;
	
	@Resource(name="ProductService") ProductService ps;
	
	@RequestMapping(value="/admin.do")
	public String admin() {
		return "admin/adminLoginForm";
	}
	
	
	
	@RequestMapping(value="/adminLogin.do")
	public String adminLogin( HttpServletRequest request, Model model, 
			@RequestParam("workId") String workId, @RequestParam("workPwd") String workPwd) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "ref_cursor", null );
		paramMap.put("workId", workId);
		as.getAdmin(paramMap);	 // 조회 
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		if(list.size() == 0) {  // 입력한 아이디 없다면
			model.addAttribute("message" , "아이디가 없어요");
			return "admin/adminLoginForm";
		}
		HashMap<String, Object> resultMap = list.get(0); 
		if(resultMap.get("PWD")==null) {
			model.addAttribute("message" , "관리자에게 문의하세요");
			return "admin/adminLoginForm";
		}else if( workPwd.equals( (String)resultMap.get("PWD") ) ) {
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", resultMap);
			return "redirect:/productList.do";
		}else {
			model.addAttribute("message" , "비번이 안맞아요");
			return "admin/adminLoginForm";
		}
	}
	
	
	@RequestMapping(value="/productList.do")
	public String product_list(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		int page = 1;
		if(  request.getParameter("first") != null  ) {
			page=1;
			session.removeAttribute("page");
			session.removeAttribute("key");
		}
		
		if( request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		}else if( session.getAttribute("page") != null ) {
			page = (Integer)session.getAttribute("page");
		}else {
			page = 1;
			session.removeAttribute("page");
		}
		
		String key = "";
		if( request.getParameter("key") != null ) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		} else if( session.getAttribute("key")!= null ) {
			key = (String)session.getAttribute("key");
		} else {
			session.removeAttribute("key");
			key = "";
		}
		
		Paging paging = new Paging();
		paging.setPage(page);
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "cnt", 0 );
		as.getAllCount( paramMap );
		int cnt = Integer.parseInt( paramMap.get("cnt").toString() );
		paging.setTotalCount( cnt );

		
		paramMap.put("startNum" , paging.getStartNum() );
		paramMap.put("endNum", paging.getEndNum() );
		paramMap.put("key", key);
		paramMap.put( "ref_cursor", null );
		as.productList(paramMap);	 // 조회 
		
		ArrayList< HashMap<String,Object> > list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		model.addAttribute("productList", list);
		model.addAttribute("paging", paging);
		model.addAttribute("key", key);
		
		return "admin/product/productList";
	}
	
	@RequestMapping("productWriteForm.do")
	public ModelAndView product_write_form( HttpServletRequest request) {
		String kindList[] = { "Heels", "Boots", "Sandals", "Shcakers", "Slippers",  "Sale" };
		ModelAndView mav = new ModelAndView();
		mav.addObject("kindList", kindList);
		mav.setViewName("admin/product/productWriteForm");
		return mav;
	}
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value="/fileup.do", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> fileup(HttpServletRequest request) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		String savePath = context.getRealPath("/product_images");
		String filename = "";
		try {
			MultipartRequest multi = new MultipartRequest(
				request, savePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			filename = multi.getFilesystemName("image");
		} catch(IOException e) {e.printStackTrace();
		}
		resultMap.put("FILENAME", "abc.txt");
		resultMap.put("STATUS", 1);
		resultMap.put("IMG", filename);
		return resultMap;
	}
	
	@RequestMapping(value="/productWrite.do", method=RequestMethod.POST)
	public String productWrite(HttpServletRequest request) {
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("kind",request.getParameter("kind"));
		paramMap.put("name",request.getParameter("name"));
		paramMap.put("price",Integer.parseInt(request.getParameter("price")));
		paramMap.put("price2",Integer.parseInt(request.getParameter("price2")));
		paramMap.put("price3",Integer.parseInt(request.getParameter("price3")));
		paramMap.put("content",request.getParameter("content"));
		paramMap.put("image",request.getParameter("image"));
		as.insertProduct(paramMap);
		return "redirect:/productList.do";
	}
	
	@RequestMapping("adminProductDetail.do")
	public String adminProductDetail( HttpServletRequest request, Model model,
			@RequestParam("pseq") int pseq) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pseq", pseq);
		paramMap.put("ref_cursor", null);
		
		ps.getProduct(paramMap); // 조회
		
		ArrayList< HashMap<String,Object> > list 
		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
	
		String kindList[] = { "0", "Heels", "Boots", "Sandals", "Shcakers", "Slippers",  "Sale" };
		int index = Integer.parseInt(list.get(0).get("KIND").toString() ); // list.get(0) 을 resultMap 변수에 넣을 수 있음
		
		model.addAttribute("productVO",list.get(0));
		model.addAttribute("kind",kindList[index]);
		return "admin/product/productDetail";
	}
	
	@RequestMapping("productUpdateForm.do")
	public String productUpdateForm( HttpServletRequest request, Model model,
			@RequestParam("pseq") int pseq) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pseq", pseq);
		paramMap.put("ref_cursor", null);
		
		ps.getProduct(paramMap); // 조회
		
		ArrayList< HashMap<String,Object> > list 
		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
	
		model.addAttribute("dto",list.get(0));
		
		String kindList[] = { "Heels", "Boots", "Sandals", "Shcakers", "Slippers",  "Sale" };
		model.addAttribute("kindList",kindList);
		return "admin/product/productUpdate";
	}
	
	@RequestMapping(value="/productUpdate.do", method=RequestMethod.POST)
	public String productUpdate(HttpServletRequest request) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("kind",request.getParameter("kind"));
		paramMap.put("name",request.getParameter("name"));
		paramMap.put("price",Integer.parseInt(request.getParameter("price")));
		paramMap.put("price2",Integer.parseInt(request.getParameter("price2")));
		paramMap.put("price3",Integer.parseInt(request.getParameter("price3"))
				-Integer.parseInt(request.getParameter("price")));
		paramMap.put("content",request.getParameter("content"));
		System.out.println("image : " + request.getParameter("image"));
		System.out.println("oldimage : " + request.getParameter("oldimage"));
		if( request.getParameter("image") == null )
			paramMap.put("image", request.getParameter("oldimage") );
		else paramMap.put("image",request.getParameter("image"));
		paramMap.put("pseq", Integer.parseInt(request.getParameter("pseq")));
		
		
		as.updateProduct(paramMap);
		return "redirect:/adminProductDetail.do?pseq="+Integer.parseInt(request.getParameter("pseq"));
	}
}











