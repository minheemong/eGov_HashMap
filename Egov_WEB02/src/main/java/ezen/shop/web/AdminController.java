package ezen.shop.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import ezen.shop.service.AdminService;

@Controller
public class AdminController {

	@Resource(name="AdminService") AdminService as;
	
}
