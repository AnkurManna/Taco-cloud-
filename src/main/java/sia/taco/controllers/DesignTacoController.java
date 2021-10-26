package sia.taco.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import sia.taco.data.IngredientRepository;
import sia.taco.data.TacoRepository;
import sia.taco.models.Ingredient;
import sia.taco.models.Ingredient.Type;
import sia.taco.models.Order;
import sia.taco.models.Taco;
import sia.taco.utils.*;
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
 
	private final IngredientRepository ingredientRepo;
	private TacoRepository designRepo;
	 @Autowired
	 public DesignTacoController(IngredientRepository ingredientRepo ,
			 TacoRepository designRepo) 
	 {
	 this.ingredientRepo = ingredientRepo;
	 this.designRepo = designRepo;
	 }
	 
	 @ModelAttribute(name = "order")
	 public Order orders() {
		 log.info("injecting new order object");
	 return new Order();
	 }
	 @ModelAttribute(name = "taco")
	 public Taco taco() {
		 
		 log.info("injecting new taco object");
	 return new Taco();
	 }
	 
	@GetMapping
 public String showDesignForm(Model model,HttpServletRequest httpServletRequest,@ModelAttribute Order order) 
 {
		/*Order inSes = (Order)httpServletRequest.getSession(false).getAttribute("order");
		   if(inSes==null)
		   {
			   log.info("NULL inSES");
		   }
		   else
		   {
			   log.info(" session " + inSes);
		   }*/
		log.info("in showDesignForm");
		log.info("Processing design: " + order);
		
		
		 
	 List<Ingredient> ingredients = new ArrayList<>();
	 ingredientRepo.findAll().forEach(i -> ingredients.add(i));
	 Type[] types = Ingredient.Type.values();
	 for (Type type : types) {
	 model.addAttribute(type.toString().toLowerCase(),
	 Utility.filterByTypes(ingredients, type));
	 }
	 model.addAttribute("design", new Taco());
	 //System.out.print(model.toString());
	 
	 
	 return "design";
 }
 
 @PostMapping
 public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute("order") Order order,Model model,HttpServletRequest httpServletRequest)
 {
	 log.info("in processDesignForm");
	 if (errors.hasErrors()) {
		 return "redirect:/design";
		 }
	
	 log.info("submitted Taco: " + taco);
	 
	 Taco saved = designRepo.save(taco);
	 order.addDesign(saved);
	 
	/* log.info("submitted Taco: " + taco);
	 //log.info("saved Taco: " + saved);
	 log.info("modified order: " + order);*/
	 model.addAttribute("order",order);
	
	 return "redirect:/orders/current";
 }
}