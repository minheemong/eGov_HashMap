package ezen.shop.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import ezen.shop.service.OrderService;

@Controller
public class OrderController {

	@Resource(name="OrderService")OrderService ms;
}
