package sia.taco.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

		@GetMapping("/")
		public String home()
		{
			/*
			 * it is the logical name of view
			 * */
			return "home";
		}
}
