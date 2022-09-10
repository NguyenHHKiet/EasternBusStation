package com.coeding.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coeding.springmvc.config.PaypalPaymentIntent;
import com.coeding.springmvc.config.PaypalPaymentMethod;
import com.coeding.springmvc.dto.BookingsDTO;
import com.coeding.springmvc.entity.Bookings;
import com.coeding.springmvc.entity.BusData;
import com.coeding.springmvc.helper.ObjectCreationHelper;
import com.coeding.springmvc.repository.BookingsRepository;
import com.coeding.springmvc.repository.BusDataRepository;
import com.coeding.springmvc.service.PaypalService;
import com.coeding.springmvc.util.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaymentController {
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaypalService paypalService;
	
	@Autowired
	private BookingsRepository bookingsRepository;
	
	@GetMapping("/pay/{id}")
	public String PayPal(@PathVariable int id, Model model) {
		Bookings busdata = bookingsRepository.findById(id);

		model.addAttribute("record", busdata);
		
		return "pay";
	}

	@PostMapping("/pay")
	public String pay(HttpServletRequest request, @RequestParam("totalCalculated") double totalCalculated) {
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(totalCalculated, "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay() {
		return "cancel";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				return "success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
}