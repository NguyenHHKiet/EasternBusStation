package com.coeding.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.coeding.springmvc.config.ApplicationProperty;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@GetMapping("/")
	public String home() {
		logger.info("Welcome home!", ApplicationProperty.userName);
		logger.info("Welcome home!", ApplicationProperty.userEmail);
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		return "index";
	}
}
