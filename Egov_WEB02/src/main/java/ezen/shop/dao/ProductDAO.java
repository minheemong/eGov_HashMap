package ezen.shop.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="ProductDAO")
public interface ProductDAO {

	void getBestList(HashMap<String, Object> paramMap);
	void getNewList(HashMap<String, Object> paramMap);
	void getKindList(HashMap<String, Object> paramMap);

}
