package ezen.shop.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.ProductDAO;
import ezen.shop.service.ProductService;

@Service(value="ProductService")
public class ProductServieimpl extends EgovAbstractServiceImpl implements ProductService{

	@Resource(name="ProductDAO") ProductDAO pdao;

	@Override
	public void getBestList(HashMap<String, Object> paramMap) {
		pdao.getBestList( paramMap );		
	}
	@Override
	public void getNewList(HashMap<String, Object> paramMap) {
		pdao.getNewList( paramMap );		
	}
	@Override
	public void getKindList(HashMap<String, Object> paramMap) {
		pdao.getKindList( paramMap );		
	}
	@Override
	public void getProduct(HashMap<String, Object> paramMap) {
		pdao.getProduct( paramMap );		
	}
	
}
