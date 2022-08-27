package com.coeding.springmvc.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.coeding.springmvc.dto.BookingsDTO;
import com.coeding.springmvc.dto.UserRegisteredDTO;
import com.coeding.springmvc.entity.Bookings;
import com.coeding.springmvc.entity.User;

public interface DefaultUserService extends UserDetailsService {

	User save(UserRegisteredDTO userRegisteredDTO);

	Bookings updateBookings(BookingsDTO bookingDTO,UserDetails user);
	
	void sendEmail(BookingsDTO bookingDTO, User users, String nameGenrator);
}
