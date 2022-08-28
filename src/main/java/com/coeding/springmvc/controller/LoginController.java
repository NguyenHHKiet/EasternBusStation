package com.coeding.springmvc.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coeding.springmvc.config.ApplicationProperty;
import com.coeding.springmvc.dto.UserLoginDTO;
import com.coeding.springmvc.service.DefaultUserService;

@Controller
@RequestMapping({ "/login", "/" })
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private DefaultUserService userService;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

	@PostMapping
	public String loginUser(@ModelAttribute("user") UserLoginDTO userLoginDTO) {
		System.out.println("UserDTO" + userLoginDTO);

		UserDetails user = userService.loadUserByUsername(userLoginDTO.getUsername());
		System.out.println(user.getPassword().equals(passwordEncoder.encode(userLoginDTO.getPassword())));
		if (user.getPassword().equals(passwordEncoder.encode(userLoginDTO.getPassword()))) {
			System.out.println("dashboard");
			return "redirect:/dashboard";
		} else
			System.out.println("login?error");
			return "redirect:/login?error";
	}
}
