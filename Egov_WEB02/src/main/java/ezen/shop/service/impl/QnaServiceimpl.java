package ezen.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ezen.shop.dao.QnaDAO;
import ezen.shop.service.QnaService;

@Service(value="QnaService")
public class QnaServiceimpl extends EgovAbstractServiceImpl implements QnaService{

	@Resource(name="QnaDAO") QnaDAO qdao;
	
}
