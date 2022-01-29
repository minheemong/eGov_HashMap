package ezen.shop.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import ezen.shop.service.QnaService;

@Controller
public class QnaController {

	@Resource(name="QnaService") QnaService ms;
	
}
