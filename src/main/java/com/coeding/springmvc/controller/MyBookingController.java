package com.coeding.springmvc.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coeding.springmvc.dto.BookingsDTO;
import com.coeding.springmvc.dto.UserLoginDTO;
import com.coeding.springmvc.entity.Bookings;
import com.coeding.springmvc.entity.User;
import com.coeding.springmvc.helper.ObjectCreationHelper;
import com.coeding.springmvc.repository.BookingsRepository;
import com.coeding.springmvc.repository.UserRepository;
import com.coeding.springmvc.service.DefaultUserService;

@Controller
@RequestMapping("/myBooking")
public class MyBookingController {

	@Autowired
	private DefaultUserService userService;

	public MyBookingController(DefaultUserService userService) {
		super();
		this.userService = userService;
	}

	@Autowired
	BookingsRepository bookingsRepository;

	@Autowired
	UserRepository userRepository;

	@ModelAttribute("bookings")
	public BookingsDTO bookingDto() {
		return new BookingsDTO();
	}

	@GetMapping
	public String login(Model model, @ModelAttribute("user") UserLoginDTO users) {
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		UserDetails users = (UserDetails) securityContext.getAuthentication().getPrincipal();
		System.out.println(users.getUsername());
		User user = userRepository.findByEmail(users.getUsername());
		List<BookingsDTO> bks = new ArrayList<BookingsDTO>();
		List<Bookings> bs = bookingsRepository.findByUserId(1);// user.getId()
		for (Bookings bookings : bs) {
			BookingsDTO bkks = ObjectCreationHelper.createBookingsDTO(bookings);
			bks.add(bkks);
		}
		model.addAttribute("userDetails", "HoangKiet NguyenHuu");// user.getName()
		Collections.reverse(bks);
		model.addAttribute("bookings", bks);
		return "myBookings";
	}

	@GetMapping("/generate/{id}")
	public String bookPage(@PathVariable int id, Model model) {
		Bookings busdata = bookingsRepository.findById(id);
		User users = userRepository.findById(busdata.getUserId());
		String user = users.getName();
		BookingsDTO bks = ObjectCreationHelper.createBookingsDTO(busdata);
		userService.sendEmail(bks, users, busdata.getFileName());
		model.addAttribute("userDetails", user);
		List<Bookings> bs = bookingsRepository.findByUserId(users.getId());
		Collections.reverse(bs);
		model.addAttribute("bookings", bs);
		return "redirect:/myBooking?success";
	}

	@GetMapping("/cancel/{id}")
	public String cancelBooking(@PathVariable int id, Model model) {
		Bookings busdata = bookingsRepository.findById(id);
		if (busdata.isTripStatus() == false) {
			setData(busdata, model);
			return "redirect:/myBooking?alreadyCancel";
		} else {
			setData(busdata, model);
			busdata.setTripStatus(false);
			bookingsRepository.create(busdata);

			return "redirect:/myBooking?successCancel";

		}
	}

	private void setData(Bookings busData, Model model) {
		User users = userRepository.findById(busData.getUserId());
		List<Bookings> bs = bookingsRepository.findByUserId(users.getId());
		Collections.reverse(bs);
		model.addAttribute("bookings", bs);
	}
}
