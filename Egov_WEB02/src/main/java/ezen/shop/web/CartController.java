package ezen.shop.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import ezen.shop.service.CartService;

@Controller
public class CartController {

	@Resource(name="CartService") CartService cs;
}
