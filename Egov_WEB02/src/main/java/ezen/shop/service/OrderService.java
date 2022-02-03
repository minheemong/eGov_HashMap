package ezen.shop.service;

import java.util.HashMap;

public interface OrderService {

	void insertOrder(HashMap<String, Object> paramMap);

	void listOrderByOseq(HashMap<String, Object> paramMap);

	void listOrderByResult(HashMap<String, Object> paramMap);

}
