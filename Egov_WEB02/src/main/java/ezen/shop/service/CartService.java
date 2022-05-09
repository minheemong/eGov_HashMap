package ezen.shop.service;

import java.util.HashMap;

public interface CartService {

	void insertCart(HashMap<String, Object> paramMap);
	void listCart(HashMap<String, Object> paramMap);
	void deleteCart(HashMap<String, Object> paramMap);

}
