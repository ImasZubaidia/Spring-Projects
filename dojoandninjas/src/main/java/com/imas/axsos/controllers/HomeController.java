package com.imas.axsos.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.imas.axsos.models.Dojo;
import com.imas.axsos.models.Ninja;
import com.imas.axsos.services.DojoService;
import com.imas.axsos.services.NinjaService;

@Controller
public class HomeController {
	private final NinjaService ninjaService;
	private final DojoService dojoService;
	public HomeController(NinjaService ninjaService,DojoService dojoService) {
		this.ninjaService = ninjaService;
		this.dojoService=dojoService;
	}
	
	@RequestMapping("/dojos/new")
	public String dojo(Model model,@ModelAttribute("dojo")Dojo language) {
		model.addAttribute("dojos", dojoService.allDojos());
		return "dojo.jsp";
	}
	@RequestMapping(value="/dojos/new", method=RequestMethod.POST)
	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			
			return "redirect:/dojos/new";
		}else {
			dojoService.createDojo(dojo);
			return "redirect:/ninjas/new";
		}
	}
	
	@RequestMapping("/ninjas/new")
	public String ninja(Model model,@ModelAttribute("ninja")Ninja ninja) {
		model.addAttribute("dojos", dojoService.allDojos());
		model.addAttribute("ninjas", ninjaService.allNinjas());
		return "ninja.jsp";
	}
	
	@RequestMapping(value="/ninjas/new", method=RequestMethod.POST)
	public String createDojo(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result, Model model) {
		if(result.hasErrors()) {
			
			return "redirect:/ninjas/new";
		}else {
			ninjaService.createNinja(ninja);
			return "redirect:/dojos/"+ninja.getDojo().getId();
		}
	}
	
	@RequestMapping(value="/dojos/{id}", method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		Dojo dojo = dojoService.findDojo(id);
		model.addAttribute("dojo", dojo);
		return "show.jsp";
	}

}