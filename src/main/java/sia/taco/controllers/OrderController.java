package sia.taco.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import lombok.extern.slf4j.Slf4j;
import sia.taco.data.OrderRepository;
import sia.taco.data.UserRepository;
import sia.taco.models.Order;
import sia.taco.models.User;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;
	private UserRepository userRepo;
	 public OrderController(OrderRepository orderRepo,UserRepository userRepo) {
	 this.orderRepo = orderRepo;
	 this.userRepo = userRepo;
	 }
	 
	 @GetMapping("/current")
	 public String orderForm(Model model) {
	 //model.addAttribute("order", new Order());
	 return "orderForm";
 }
	 @PostMapping
	 public String processOrder(@ModelAttribute Order order ,Errors errors,SessionStatus sessionStatus,
			 Principal principal) {
	  
		 log.info(errors.toString());
		 if (errors.hasErrors()) {
			 return "redirect:/orderForm";
			 }
		 User user = userRepo.findByUsername(principal.getName());
		 order.setUser(user);
		 log.info("Order submitted: " + order);
		 orderRepo.save(order);
		 sessionStatus.setComplete();
		 log.info("Order submitted: " + order);
	  return "redirect:/";
	 }
}