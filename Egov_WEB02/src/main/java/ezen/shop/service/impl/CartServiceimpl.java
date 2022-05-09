package ezen.shop.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.CartDAO;
import ezen.shop.service.CartService;

@Service(value="CartService")
public class CartServiceimpl extends EgovAbstractServiceImpl implements CartService{

	@Resource(name="CartDAO") CartDAO cdao;

	@Override
	public void insertCart(HashMap<String, Object> paramMap) {
		cdao.insertCart( paramMap );
	}
	@Override
	public void listCart(HashMap<String, Object> paramMap) {
		cdao.listCart(paramMap);		
	}
	@Override
	public void deleteCart(HashMap<String, Object> paramMap) {
		cdao.deleteCart( paramMap );		
	}
}
