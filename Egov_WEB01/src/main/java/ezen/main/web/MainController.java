package ezen.main.web;

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
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ezen.main.dto.BoardVO;
import ezen.main.dto.MemberVO;
import ezen.main.dto.ReplyVO;
import ezen.main.dto.TransferVO;
import ezen.main.dto.TransferVO2;
import ezen.main.service.MainService;
import ezen.main.util.Paging;

@Controller
public class MainController {

	@Autowired
	ServletContext context;

	@Resource(name = "MainService")
	MainService ms;
	// @Service가 달려있는 클래스의 이름은 MainServiceimpl이지만, 어너테이션의 value값이 MainService 이기 때문에
	// @Resource를 이용하여 어너테이션 값(value="MainService")을 검색하여 매칭합니다

	@RequestMapping(value = "/main.do")
	public String main(Model model, HttpServletRequest request) {
		model.addAttribute("food", "삼계탕");
		return "main";
	}

	@RequestMapping(value = "/main2.do")
	public String main2(Model model, HttpServletRequest request) {
		String menu = request.getParameter("menu");

		model.addAttribute("food", menu);
		return "main2";
	}

	@RequestMapping(value = "/loginForm.do")
	public String login_form(Model model, HttpServletRequest request) {
		return "loginForm";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam("pw") String pw, Model model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String url = "redirect:/boardList.do";
		
		//MemberVO mvo = ms.getMember(id);
		TransferVO con = ms.getMember(id);
		// MemberVO mvo = (MemberVO)con.getList().get(0); 를 여기서 쓰지 않는 이유는 getList로 얻은 list가 null이면,
		// get(0)이 에러를 발생하는 원인이 되기 때문입니다
		if ( con.getList().size() == 0) {
			model.addAttribute("message", "id를 확인하세요");
		} 
		
		MemberVO mvo = (MemberVO)con.getList().get(0);

		if (mvo.getPwd() == null) {
			model.addAttribute("message", "비번이 null 입니다. 관리자에 문의 하세요");
		} else if (!mvo.getPwd().equals(pw)) {
			model.addAttribute("message", "비밀번호가 맞지 않습니다");
		} else if (mvo.getPwd().equals(pw)) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			url = "redirect:/boardList.do";
		} else {
			model.addAttribute("message", "로그인 정보가 일치하지 않습니다");
		}
		return url;
	}

	@RequestMapping(value = "/boardList.do")
	public String board_list(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null)
			return "loginForm";
		else {
			int page = 1;
			
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if ( session.getAttribute("page")!=null) {
				page = (int) session.getAttribute("page");
			} else {
				page =1;
				session.removeAttribute("page");
			}
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = ms.getAllCount();
			paging.setTotalCount(count);
			

			//List<BoardVO> list = ms.selectBoard();
			//model.addAttribute("boardList", list);
			// List에는 별도의 오라클 프로시저에서 getter와 setter가 존재하지 않기 때문에 사용 불가
			
			//ArrayList<BoardVO> list = bs.selectBoard(paging);
			TransferVO con = ms.selectBoard(paging);
			model.addAttribute("paging", paging);
			model.addAttribute("boardList", con.getList()); // con.getList()  <-  List<BoardVO>
			// 1. con.getList()를 반복실행해서 getNum()으로 얻은 게시물 번호로 댓글 개수 조회 후 리스트 각 게시물에 set하는 방법
			// 2. selectBoard 프로시저 수정하여 replycnt 구하는 방법
			

			return "main";
		}
	}

	@RequestMapping(value = "/boardView.do")
	public ModelAndView board_view(@RequestParam("num") int num, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		//BoardVO bdto = ms.getBoardOne(num);
		//mav.addObject("board", bdto);
		
		TransferVO con = ms.getBoardOne(num);
		model.addAttribute("board",con.getList().get(0));

		TransferVO2 con2 = ms.selectReply(num);

		mav.addObject("replyList", con2.getList());
		mav.setViewName("boardView");
		return mav;
	}
	
	@RequestMapping(value="/addReply.do", method = RequestMethod.POST)
	public String addReply(@RequestParam("boardnum") int boardnum, 
			@RequestParam("userid") String userid,
			@RequestParam("reply") String reply, HttpServletRequest request) {
		
		ReplyVO rvo = new ReplyVO();
		
		rvo.setUserid(userid);
		rvo.setContent(reply);
		rvo.setBoardnum(boardnum);
		
		ms.addReply(rvo);
			
		return "redirect:/boardViewWithoutCount.do?num=" + boardnum;
	}
	
	@RequestMapping(value="/deleteReply.do")
	public String addReply(@RequestParam("num") int num, 
			@RequestParam("boardnum") int boardnum, HttpServletRequest request) {
		
		ReplyVO rvo = new ReplyVO();
		
		System.out.println("num : " + num);
		
		ms.deleteReply(num);
			
		return "redirect:/boardViewWithoutCount.do?num=" + boardnum;
	}
	
	@RequestMapping(value = "/boardViewWithoutCount.do")
	public ModelAndView board_view_without_count(
			@RequestParam("num") int num, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		//BoardVO bdto = ms.getBoardOne(num);
		//mav.addObject("board", bdto);
		
		TransferVO con = ms.getBoardOneWithoutCount(num);
		model.addAttribute("board",con.getList().get(0));

		TransferVO2 con2 = ms.selectReply(num);

		mav.addObject("replyList", con2.getList());
		mav.setViewName("boardView");
		return mav;
	}

	@RequestMapping(value = "/joinForm.do")
	public String join_form(Model model, HttpServletRequest request) {
		return "member/memberJoinForm";
	}

	@RequestMapping(value = "/idcheck.do")
	public ModelAndView idcheck(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView();
		
		//MemberVO mvo = ms.getMember(id);
		TransferVO con = ms.getMember(id);
		
		if ( con.getList().size() == 0)
			mav.addObject("result", -1);
		else
			mav.addObject("result", 1);

		mav.addObject("id", id);
		mav.setViewName("member/idcheck");
		return mav;
	}

	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String join(Model model, HttpServletRequest request) {
		MemberVO sm = new MemberVO();
		sm.setId(request.getParameter("id"));
		sm.setPwd(request.getParameter("pw"));
		sm.setName(request.getParameter("name"));
		sm.setPhone(request.getParameter("phone"));
		sm.setEmail(request.getParameter("email"));

		ms.insertMember(sm);

		return "loginForm";
	}

	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/loginForm.do";
	}

	@RequestMapping("/memberEditForm.do")
	public String member_edit_form(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null)
			return "loginform";
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		MemberVO dto = new MemberVO();
		dto.setId(loginUser.getId());
		dto.setName(loginUser.getName());
		dto.setEmail(loginUser.getEmail());
		dto.setPhone(loginUser.getPhone());
		model.addAttribute("dto", dto);
		return "member/memberEditForm";
	}

	@RequestMapping(value = "/memberEdit.do", method = RequestMethod.POST)
	public String memberEdit(Model model, HttpServletRequest request) {

		MemberVO sm = new MemberVO();
		sm.setId(request.getParameter("id"));
		sm.setPwd(request.getParameter("pw"));
		sm.setName(request.getParameter("name"));
		sm.setPhone(request.getParameter("phone"));
		sm.setEmail(request.getParameter("email"));

		ms.updateMember(sm);

		// 세션에 새로운 로그인정보를 다시 저장
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", sm);

		return "redirect:/boardList.do";
	}

	@RequestMapping("/boardWriteForm.do")
	public String write_form(Model model, HttpServletRequest request) {
		String url = "boardWriteForm";
		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") == null)
			url = "loginform";
		return url;
	}

	@RequestMapping(value = "/boardWrite.do", method = RequestMethod.POST)
	public String board_write(Model model, HttpServletRequest request) {

		String path = context.getRealPath("/images");
		try {
			
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			
			BoardVO sb = new BoardVO();
			sb.setPass(multi.getParameter("pass"));
			sb.setUserid(multi.getParameter("userid"));
			sb.setEmail(multi.getParameter("email"));
			sb.setTitle(multi.getParameter("title"));
			sb.setContent(multi.getParameter("content"));
			if(multi.getFilesystemName("imgfilename")==null) sb.setImgfilename("");
			else sb.setImgfilename(multi.getFilesystemName("imgfilename"));
			
			ms.insertBoard(sb);
			
		} catch (Exception e) {e.printStackTrace();
		}
		
		return "redirect:/boardList.do";
	}
	

	@RequestMapping("/boardEditForm.do")
	public String board_edit_form(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		model.addAttribute("num", num);
		return "boardCheckPassForm";
	}
	

	@RequestMapping("/boardEdit.do")
	public String board_edit(Model model, HttpServletRequest request) { 
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		
		//BoardVO sb = ms.getBoard(num);
		TransferVO con = ms.getBoardOneWithoutCount(Integer.parseInt(num));
		BoardVO sb = (BoardVO)con.getList().get(0);
		model.addAttribute("num",num);
		if(pass.equals(sb.getPass())) {
			return "boardCheckPass";
		} else {
			model.addAttribute("message","비밀번호가 맞지 않습니다. 확인해주세요.");
			return "boardCheckPassForm";
		}
	}
	

	@RequestMapping("boardUpdateForm.do")
	public String board_update_form(Model model, HttpServletRequest request) { 
		String num = request.getParameter("num");
		//BoardVO sb =  ms.getBoard(num);
		TransferVO con = ms.getBoardOneWithoutCount(Integer.parseInt(num));
		model.addAttribute("num",num);
		model.addAttribute("board", con.getList().get(0));
		return "boardEditForm";
	}
	

	@RequestMapping(value="boardUpdate.do", method = RequestMethod.POST)
	public String board_update(Model model, HttpServletRequest request) { 
		String path = context.getRealPath("/images");
		BoardVO sb = new BoardVO();
		int num = 0;
		try {

			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			
			num = Integer.parseInt(multi.getParameter("num"));
			sb.setNum(num);
			sb.setPass(multi.getParameter("pass"));
			sb.setUserid(multi.getParameter("userid"));
			sb.setEmail(multi.getParameter("email"));
			sb.setTitle(multi.getParameter("title"));
			sb.setContent(multi.getParameter("content"));
			if(multi.getFilesystemName("imgfilename")==null) sb.setImgfilename("oldfilename");
			else sb.setImgfilename(multi.getFilesystemName("imgfilename"));

			ms.boardUpdate(sb);
			
		} catch (Exception e) {e.printStackTrace();
		}
		return "redirect:/boardViewWithoutCount.do?num=" + num;
	}
	
	
	@RequestMapping("boardDeleteForm.do")
	public String board_delete_form(Model model, HttpServletRequest request) { 
		String num = request.getParameter("num");
		//BoardVO sb =  ms.getBoard(num);
		//TransferVO con = ms.getBoardOneWithoutCount(Integer.parseInt(num));
		model.addAttribute("num",num);
		//model.addAttribute("board",con.getList().get(0));
		return "boardCheckPassForm";
	}
	
	@RequestMapping("boardDelete.do")
	public String board_delete(Model model, HttpServletRequest request) { 
		String num = request.getParameter("num");
		ms.boardDelete(num);
		return "redirect:/boardList.do";
	}
}
