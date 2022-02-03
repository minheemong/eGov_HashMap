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
	public void cartInsert(HashMap<String, Object> paramMap) {
		cdao.cartInsert(paramMap);
	}

	@Override
	public void cartList(HashMap<String, Object> paramMap) {
		cdao.cartList(paramMap);
	}

	@Override
	public void cartDelete(HashMap<String, Object> paramMap) {
		cdao.cartDelete(paramMap);
	}
}
