package ezen.shop.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.QnaDAO;
import ezen.shop.service.QnaService;

@Service(value="QnaService")
public class QnaServiceimpl extends EgovAbstractServiceImpl implements QnaService{

	@Resource(name="QnaDAO") QnaDAO qdao;

	@Override
	public void listQna(HashMap<String, Object> paramMap) {
		qdao.listQna( paramMap );		
	}
	@Override
	public void getQna(HashMap<String, Object> paramMap) {
		qdao.getQna( paramMap );
	}
	@Override
	public void insertQna(HashMap<String, Object> paramMap) {
		qdao.insertQna( paramMap );
	}
	
}
