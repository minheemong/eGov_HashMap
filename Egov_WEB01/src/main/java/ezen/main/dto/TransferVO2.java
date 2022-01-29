package ezen.main.dto;

import java.util.List;

import ezen.main.util.Paging;

// parameter가 될 수도 있고, result가 될 수도 있는 class
// 검색할 게시물 번호와 검색결과를 담을 수 있는 멤버변수가 있는 클래스
public class TransferVO2 {
	private int num; // 검색용 게시물 번호를 저장할 변수이며, 프로시저의 IN 변수에 전해질 변수입니다
	private List list;	// 검색 결과 게시물의 리스트가 저장될 변수이며, 프로시저의 OUT 변수에 전해질 변수입니다
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
}
