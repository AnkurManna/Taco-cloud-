package sia.taco.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import sia.taco.models.Order;

class counter
{
	public ArrayList<Integer> lst;
	public counter()
	{
		lst = new ArrayList<Integer>();
	}
}
@Controller
@Slf4j
@SessionAttributes("ctx")
public class SessionController {

	 @ModelAttribute(name = "ctx")
	 public counter orders() {
		 log.info("injecting new counter object");
	 return new counter();
	 }
	@GetMapping("/first")
	public String setSessionAttribute(Model model,@ModelAttribute("ctx") counter ctr)
	{
		//System.out.println(model.getAttribute("channelName"));	
		ctr.lst.add(1);
		return "home";
	}
	@GetMapping("/second")
	public String display(HttpSession session,Model model,@ModelAttribute("ctx") counter ctr)
	{
		//System.out.print(session.getAttribute("channelName"));
		ctr.lst.add(1);
		return "home";
	}
	
	@GetMapping("/third")
	public String display2(HttpSession session,Model model,@ModelAttribute("ctx") counter ctr)
	{
		//System.out.print(session.getAttribute("channelName"));
		System.out.println("ctx value " + ctr.lst);	
		return "home";
	}
}
