package ezen.shop.service;

import java.util.HashMap;

public interface ProductService {

	void getBestList(HashMap<String, Object> paramMap);
	void getNewList(HashMap<String, Object> paramMap2);
	void getKindList(HashMap<String, Object> paramMap);
	void getProduct(HashMap<String, Object> paramMap);

}
