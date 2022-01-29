package ezen.main.dao;

import java.util.ArrayList;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import ezen.main.dto.BoardVO;
import ezen.main.dto.MemberVO;
import ezen.main.dto.ReplyVO;
import ezen.main.dto.TransferVO;
import ezen.main.dto.TransferVO2;

@Mapper(value="MainDAO")
public interface MainDAO {

	void selectBoard(TransferVO container);

	void getBoardOne(TransferVO container);
	
	void getMember(TransferVO container);

	void insertBoard(BoardVO bdto);
	
	void getBoard(TransferVO container);
	
	void getAllCount(TransferVO con);

	void selectReply(TransferVO2 con);

	void insertMember(MemberVO sm);

	void updateMember(MemberVO sm);

	void boardUpdate(BoardVO sb);

	void boardDelete(int num);

	void getBoardOneWithoutCount(TransferVO container);

	void addReply(ReplyVO rvo);

	void deleteReply(int num);

}
