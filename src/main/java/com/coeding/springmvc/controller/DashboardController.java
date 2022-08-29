package com.coeding.springmvc.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coeding.springmvc.dto.BookingsDTO;
import com.coeding.springmvc.dto.ReservationDTO;
import com.coeding.springmvc.entity.Bookings;
import com.coeding.springmvc.entity.BusData;
import com.coeding.springmvc.entity.User;
import com.coeding.springmvc.helper.ObjectCreationHelper;
import com.coeding.springmvc.repository.BookingsRepository;
import com.coeding.springmvc.repository.BusDataRepository;
import com.coeding.springmvc.repository.UserRepository;
import com.coeding.springmvc.service.DefaultUserService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private DefaultUserService userService;

	public DashboardController(DefaultUserService userService) {
		super();
		this.userService = userService;
	}

	@Autowired
	BookingsRepository bookingsRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BusDataRepository busDataRepository;

	@ModelAttribute("reservation")
	public ReservationDTO reservationDTO() {
		return new ReservationDTO();
	}

	@GetMapping
	public String displayDashboard(Model model,Authentication authentication) {
		System.out.println(authentication.getName());
		
		String user = returnUsername();
		model.addAttribute("userDetails", user);
		return "dashboard";
	}

	@PostMapping
	public String filterBusData(@ModelAttribute("reservation") ReservationDTO reservationDTO, Model model
			) {
		List<BusData> busData = busDataRepository.findByToFromAndDate(reservationDTO.getTo(), reservationDTO.getFrom(),
				reservationDTO.getFilterDate());

		if (busData.isEmpty()) {
			busData = null;
		}
		String user = returnUsername();
		model.addAttribute("userDetails", user);

		model.addAttribute("busData", busData);
		model.addAttribute("reservation", reservationDTO);
		return "dashboard";
	}

	@GetMapping("/book/{id}")
	public String bookPage(@PathVariable int id, Model model) {
		Optional<BusData> busdata = Optional.ofNullable(busDataRepository.findById(id));
		BookingsDTO bks = ObjectCreationHelper.createBookingsDTO(busdata.get());

		String user = returnUsername();
		model.addAttribute("userDetails", user);

		model.addAttribute("record", bks);
		return "book";
	}

	@PostMapping("/booking")
	public String finalBooking(@ModelAttribute("record") BookingsDTO bookingDTO, Model model) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		UserDetails user = (UserDetails) securityContext.getAuthentication().getPrincipal();
		Bookings b = userService.updateBookings(bookingDTO, user);
		model.addAttribute("record", new BookingsDTO());
		return "redirect:/myBooking";
	}

	private String returnUsername() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		System.out.println(securityContext);
		
		Authentication authentication = securityContext.getAuthentication();
		assert(authentication.isAuthenticated());
		System.out.println(authentication);
		//authentication is null ?????
		UserDetails user = (UserDetails) authentication.getPrincipal();
		System.out.println(user.getUsername());
		User users = userRepository.findByEmail(user.getUsername());
		System.out.println(users.getName());
		return users.getName();
	}
	
//	private String returnUsername() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (principal instanceof UserDetails) {
//			return ((UserDetails) principal).getUsername();
//		}
//		if (principal instanceof Principal) {
//			return ((Principal) principal).getName();
//		}
//		return String.valueOf(principal);
//	}
}
