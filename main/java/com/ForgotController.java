package com.smart.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.service.EmailService;

@Controller
public class ForgotController {
	Random random = new Random();

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// Email Id form handler
	@GetMapping("/forgot")
	public String openEmailForm() {
		return "forgot_email_form";
	}

	@PostMapping("/send-otp")
	public String sendOTP(@RequestParam("email") String email, HttpSession session) {
		System.out.println("EMAIL: " + email);

		// Generate OTP of 5 digit

		// int otp = random.nextInt(99999);
		int otp = random.nextInt(90000) + 10000;

		System.out.println("OTP: " + otp);

		// Code for Send OTP to email
		String subject = "OTP from SCM";
		String message = "" + "<div style='border:1px solid #e2e2e2; padding:20px'>" + "<h1>" + " Your OTP is : "
				+ "<b>" + otp + "</b>" + "</h1>" + "</div>";
		String to = email;

		boolean flag = this.emailService.sendEmail(subject, message, to);
		if (flag) {
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email);
			System.out.println("VERIFY OTP " + flag);
			System.out.println("OTP VERIFIED " + otp);
			return "verify_otp";
		} else {
			session.setAttribute("message", "Check your email ID!");
			System.out.println("FORGOT EMAIL FORM " + flag);
			return "forgot_email_form";
		}

	}

	// Verify OTP
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("otp") int otp, HttpSession session) {

		int myOtp = (int) session.getAttribute("myotp");
		String email = (String) session.getAttribute("email");

		if (myOtp == otp) {
			// Password Change form
			User user = this.userRepository.getUserByUsername(email);

			if (user == null) {
				// Send error message
				session.setAttribute("message", "User does not exist with this email !");
				return "forgot_email_form";
			} else {
				// Send change password form
			}

			return "password_change_form";
		} else {
			session.setAttribute("message", "Wrong OTP entered!!!");
			return "verify_otp";
		}

	}

	// Change Password
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("newpassword") String newpassword, HttpSession session) {
		String email = (String) session.getAttribute("email");
		User user = this.userRepository.getUserByUsername(email);
		user.setPassword(this.bCryptPasswordEncoder.encode(newpassword));
		this.userRepository.save(user);

		return "redirect:/signin?change=Password changed successfully...";
	}

}
