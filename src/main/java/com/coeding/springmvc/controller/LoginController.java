package com.coeding.springmvc.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coeding.springmvc.config.ApplicationProperty;
import com.coeding.springmvc.config.SampleAuthenticationManager;
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
		logger.info("Welcome login! The client locale is {}.", locale);
		logger.info("Welcome login! The client locale is {}.", ApplicationProperty.userName);
		logger.info("Welcome login! The client locale is {}.", ApplicationProperty.userEmail);
		return "login";
	}

	@PostMapping
	public String loginUser(HttpServletRequest req,@ModelAttribute("user") UserLoginDTO userLoginDTO) {
		AuthenticationManager am = new SampleAuthenticationManager();

		System.out.println("UserDTO" + userLoginDTO);

		UserDetails user = userService.loadUserByUsername(userLoginDTO.getUsername());

		try {
			UsernamePasswordAuthenticationToken request = new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
			Authentication result = am.authenticate(request);
			
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(result);
			
			HttpSession session = req.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
		} catch (AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
		}
		System.out.println("Successfully authenticated. Security context contains: " +
	              SecurityContextHolder.getContext().getAuthentication());

		if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
			System.out.println("dashboard");
			return "redirect:/dashboard";
		} else
			System.out.println("login?error");
		return "redirect:/login?error";
	}
}
