package sia.taco.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import sia.taco.data.UserRepository;
import sia.taco.models.RegistrationForm;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

	private UserRepository userRepo;
	 private PasswordEncoder passwordEncoder;
	 public RegistrationController(
	 UserRepository userRepo, PasswordEncoder passwordEncoder) {
	 this.userRepo = userRepo;
	 this.passwordEncoder = passwordEncoder;
	 }
	 @GetMapping
	 public String registerForm() {
	 return "registration";
	 }
	 @PostMapping
	 public String processRegistration(RegistrationForm form) {
	 userRepo.save(form.toUser(passwordEncoder));
	 log.info("form " + form.toUser(passwordEncoder));
	 return "redirect:/login";
	 }
}
