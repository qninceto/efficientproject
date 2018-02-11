package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.efficientproject.model.DAO.Vatman;

@Controller
public class VatmanController {

	@RequestMapping(value="/vatmans", method=RequestMethod.GET)
	public String showVatmanPage(Model model) {
		model.addAttribute("vatman", new Vatman());
		return "vatmans";
	}
	
	@RequestMapping(value="/vatmans", method=RequestMethod.POST)
	public String addNewVatman(@ModelAttribute Vatman vatman) {
		//add to some DAO...
		
		System.out.println(vatman);
		
		return "vatmans";
	}
}
