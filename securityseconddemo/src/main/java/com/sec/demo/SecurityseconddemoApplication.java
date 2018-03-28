package com.sec.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@SpringBootApplication
@Controller
public class SecurityseconddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityseconddemoApplication.class, args);
	}

	@GetMapping("/user")
	public String user(@AuthenticationPrincipal Principal principal, Model model){
		model.addAttribute("username", principal.getName());
		return "user/user";
	}

	@GetMapping({"/", "/index", "/home"})
	public String root() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
