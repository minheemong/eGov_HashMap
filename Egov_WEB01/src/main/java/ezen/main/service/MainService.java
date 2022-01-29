package ezen.main.service;

import java.util.ArrayList;

import ezen.main.dto.BoardVO;
import ezen.main.dto.MemberVO;
import ezen.main.dto.ReplyVO;
import ezen.main.dto.TransferVO;
import ezen.main.dto.TransferVO2;
import ezen.main.util.Paging;

public interface MainService {

	TransferVO selectBoard(Paging paging);
	
	TransferVO getBoardOne(int num);
	
	TransferVO getMember(String id);

	void insertBoard(BoardVO bdto);
	
	int getAllCount();

	TransferVO2 selectReply(int num);

	void insertMember(MemberVO sm);

	void updateMember(MemberVO sm);

	void boardUpdate(BoardVO sb);

	void boardDelete(String num);

	TransferVO getBoardOneWithoutCount(int num);

	void addReply(ReplyVO rvo);

	void deleteReply(int num);

}
