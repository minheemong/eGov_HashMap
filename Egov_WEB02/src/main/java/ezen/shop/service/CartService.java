package ezen.shop.service;

import java.util.HashMap;

public interface CartService {

	void cartInsert(HashMap<String, Object> paramMap);

	void cartList(HashMap<String, Object> paramMap);

	void cartDelete(HashMap<String, Object> paramMap);

}
