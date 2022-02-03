package ezen.shop.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="CartDAO")
public interface CartDAO {

	void cartInsert(HashMap<String, Object> paramMap);

	void cartList(HashMap<String, Object> paramMap);

	void cartDelete(HashMap<String, Object> paramMap);

}
