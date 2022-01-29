package ezen.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.CartDAO;
import ezen.shop.service.CartService;

@Service(value="CartService")
public class CartServiceimpl extends EgovAbstractServiceImpl implements CartService{

	@Resource(name="CartDAO") CartDAO cdao;
}
