package sia.taco.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import sia.taco.data.UserRepository;

@Controller
public class HomeController {

		/*@Autowired
		UserRepository repo;*/
		@GetMapping("/")
		public String home()
		{
			/*
			 * it is the logical name of view
			 * */
			/*System.out.println("------------------------------");
			System.out.println(repo.findByUsername("a"));
			System.out.println("------------------------------");*/
			return "home";
		}
}
