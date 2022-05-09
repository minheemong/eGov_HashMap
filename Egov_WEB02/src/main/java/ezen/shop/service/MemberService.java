package ezen.shop.service;

import java.util.HashMap;

public interface MemberService {

	void getMember(HashMap<String, Object> paramMap);
	void selectAddressByDong(HashMap<String, Object> paramMap);
	void insertMember(HashMap<String, Object> paramMap);
	void updateMember(HashMap<String, Object> paramMap);

}
