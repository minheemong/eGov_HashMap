package ezen.main.service.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.main.dao.MainDAO;
import ezen.main.dto.BoardVO;
import ezen.main.dto.MemberVO;
import ezen.main.dto.ReplyVO;
import ezen.main.dto.TransferVO;
import ezen.main.dto.TransferVO2;
import ezen.main.service.MainService;
import ezen.main.util.Paging;

@Service(value="MainService")
public class MainServiceimpl extends EgovAbstractServiceImpl implements MainService{

	@Resource(name="MainDAO") MainDAO mdao;
	
	@Override
	public TransferVO selectBoard(Paging paging) {
		TransferVO container = new TransferVO();
		container.setPaging(paging);
		//TransferVO 에서 StartNum과 EndNum으로 변수 선언한 경우
		//container.setStartnum(paging.startNum);
		//container.setEndnum(paging.endNum);
		mdao.selectBoard(container); 
		// DAO에 조회결과를 담을 바구니만 전달해준다. 따로 리턴 받지 않아도 바구니에 담긴 조회결과를 사용할 수 있는 구조
		return container;
	}	
	
	@Override
	public TransferVO getBoardOne(int num) {
		TransferVO container = new TransferVO();
		container.setNum(num);
		mdao.getBoardOne(container); 
		return container;
	}
	
	@Override
	public TransferVO getMember(String id) {
		TransferVO container = new TransferVO();
		container.setId(id);
		mdao.getMember(container);
		return container;
	}
	
	@Override
	public void insertBoard(BoardVO bdto) {
		mdao.insertBoard(bdto);
	}
	
	@Override
	public TransferVO getBoardOneWithoutCount(int num) {
		TransferVO container = new TransferVO();
		container.setNum(num);
		mdao.getBoardOneWithoutCount(container); 
		return container;
	}
	
	@Override
	public int getAllCount() {
		TransferVO con = new TransferVO();
		mdao.getAllCount(con); // out 변수로 TransferVO를 전달해서, 결과를 TransferVO 안의 count 변수에 담아옵니다
		return con.getCount();
	}
	
	@Override
	public void boardUpdate(BoardVO sb) {
		mdao.boardUpdate(sb);
	}

	@Override
	public TransferVO2 selectReply(int num) {
		TransferVO2 con = new TransferVO2();
		con.setNum(num);
		mdao.selectReply(con);
		return con;
	}

	@Override
	public void insertMember(MemberVO sm) {
		mdao.insertMember(sm);
	}
	
	@Override
	public void updateMember(MemberVO sm) {
		mdao.updateMember(sm);
	}

	@Override
	public void boardDelete(String num) {
		TransferVO container = new TransferVO();
		container.setNum(Integer.parseInt(num));
		mdao.boardDelete(Integer.parseInt(num));
	}

	@Override
	public void addReply(ReplyVO rvo) {
		mdao.addReply(rvo);
	}

	@Override
	public void deleteReply(int num) {
		TransferVO2 con = new TransferVO2();
		con.setNum(num);
		mdao.deleteReply(num);
	}

}
