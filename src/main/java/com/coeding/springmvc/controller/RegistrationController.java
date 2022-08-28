package com.coeding.springmvc.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coeding.springmvc.config.ApplicationProperty;
import com.coeding.springmvc.dto.UserRegisteredDTO;
import com.coeding.springmvc.service.DefaultUserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	private DefaultUserService userService;

	public RegistrationController(DefaultUserService userService) {
		super();
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegisteredDTO userRegistrationDto() {
        return new UserRegisteredDTO();
    }

    @GetMapping
    public String showRegistrationForm(Locale locale) {
    	logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("Welcome home! The client locale is {}.", ApplicationProperty.userName);
		logger.info("Welcome home! The client locale is {}.", ApplicationProperty.userEmail);
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") 
              UserRegisteredDTO registrationDto) {
        userService.save(registrationDto);
        return "redirect:/login";
    }
}
