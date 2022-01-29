package ezen.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.OrderDAO;
import ezen.shop.service.OrderService;

@Service(value="OrderService")
public class OrderServiceimpl extends EgovAbstractServiceImpl implements OrderService{

	@Resource(name="OrderDAO") OrderDAO odao;
}
