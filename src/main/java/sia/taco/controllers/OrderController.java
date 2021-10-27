package sia.taco.controllers;

import java.security.Principal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import sia.taco.utils.OrderProps;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix="taco.orders")
public class OrderController {
	
	private OrderRepository orderRepo;
	//private UserRepository userRepo;
	//private int pageSize = 20;
	private OrderProps props;
	/*public void setPageSize(int pageSize) {
		 this.pageSize = pageSize;
		 }*/
	 public OrderController(OrderRepository orderRepo, OrderProps props) {
	 this.orderRepo = orderRepo;
	 this.props = props;
	 //this.userRepo = userRepo;
	 }
	 
	 @GetMapping("/current")
	 public String orderForm(Model model) {
	 //model.addAttribute("order", new Order());
	 return "orderForm";
 }
	 @PostMapping
	 public String processOrder(@ModelAttribute Order order ,Errors errors,SessionStatus sessionStatus,
			 @AuthenticationPrincipal User user) {
	  
		 log.info(errors.toString());
		 if (errors.hasErrors()) {
			 return "redirect:/orderForm";
			 }
		 //User user = userRepo.findByUsername(principal.getName());
		 order.setUser(user);
		 log.info("Order submitted: " + order);
		 orderRepo.save(order);
		 sessionStatus.setComplete();
		 log.info("Order submitted: " + order);
	  return "redirect:/";
	 }
	 
	 @GetMapping
	 public String ordersForUser(
	  @AuthenticationPrincipal User user, Model model) {
		 Pageable pageable = PageRequest.of(0, props.getPageSize());
		 model.addAttribute("orders",
		 orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
	  return "orderList";
	 }
}