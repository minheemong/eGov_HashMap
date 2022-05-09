package ezen.shop.dao;

import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="OrderDAO")
public interface OrderDAO {

	void insertOrder(HashMap<String, Object> paramMap);
	void listOrderByOseq(HashMap<String, Object> paramMap);
	void listOrderByIdIng(HashMap<String, Object> paramMap);
	void listOseqByIdAll(HashMap<String, Object> paramMap);

}
