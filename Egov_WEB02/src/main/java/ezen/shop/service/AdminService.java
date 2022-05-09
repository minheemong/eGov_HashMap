package ezen.shop.service;

import java.util.HashMap;

public interface AdminService {

	void getAdmin(HashMap<String, Object> paramMap);
	void productList(HashMap<String, Object> paramMap);
	void getAllCount(HashMap<String, Object> paramMap);
	void insertProduct(HashMap<String, Object> paramMap);
	void updateProduct(HashMap<String, Object> paramMap);
	
}
