package ezen.shop.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="AdminDAO")
public interface AdminDAO {

	void getAdmin(HashMap<String, Object> paramMap);
	void productList(HashMap<String, Object> paramMap);
	void getAllCount(HashMap<String, Object> paramMap);
	void insertProduct(HashMap<String, Object> paramMap);
	void updateProduct(HashMap<String, Object> paramMap);

}
