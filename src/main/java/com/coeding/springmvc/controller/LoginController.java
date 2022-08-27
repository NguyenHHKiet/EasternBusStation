package com.coeding.springmvc.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coeding.springmvc.config.ApplicationProperty;
import com.coeding.springmvc.dto.UserLoginDTO;

@Controller
@RequestMapping({"/login","/"})
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@ModelAttribute("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }
    
	@GetMapping
	public String login(Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("Welcome home! The client locale is {}.", ApplicationProperty.userName);
		logger.info("Welcome home! The client locale is {}.", ApplicationProperty.userEmail);
		return "login";
	}
}
