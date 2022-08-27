package com.coeding.springmvc.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.coeding.springmvc.dto.BookingsDTO;
import com.coeding.springmvc.dto.UserRegisteredDTO;
import com.coeding.springmvc.entity.Bookings;
import com.coeding.springmvc.entity.User;
import com.coeding.springmvc.service.DefaultUserService;
import com.lowagie.text.DocumentException;

@Service
public class DefaultUserServiceImpl implements DefaultUserService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(UserRegisteredDTO userRegisteredDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bookings updateBookings(BookingsDTO bookingDTO, UserDetails user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendEmail(BookingsDTO bookingDTO, User users, String nameGenrator) {
		// TODO Auto-generated method stub
		
	}

}
