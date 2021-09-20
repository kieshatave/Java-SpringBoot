package com.kieshatave.grouplang.controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kieshatave.grouplang.models.Language;
import com.kieshatave.grouplang.services.LanguageService;

@SpringBootApplication
@Controller
public class LanguageController extends HomeController{
	
	private final LanguageService service;
	
	public LanguageController(LanguageService service) {
		this.service = service;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("language", new Language());
		model.addAttribute("languages", this.service.all());
		return "/language/index.html";
	}
	
	@RequestMapping("{id}")
	public String viewLanguage(@PathVariable Long id, RedirectAttributes attributes, Model model) {
		
		Language language = this.service.findById(id);
		
		if( language == null ) {
			attributes.addFlashAttribute("messages", new ArrayList<String>(Arrays.asList("Invalid laguage")));
			return "redirect:/language/create";
		}
		model.addAttribute("language", language);
		return "language/viewLanguage.html";
	}
	
	@GetMapping("/language/create")
	public String getAddLanguage(@ModelAttribute("language") Language language, Model model) {
		return "language/index.html";
	}
	
	@PostMapping("/language/create")
	public String addLanguage(
			@Valid Language language, 
			BindingResult result,
			RedirectAttributes attributes
			) {
		if( result.hasErrors() ) return "language/index.html";
		this.service.create(language);
		return "redirect:/";
	}
	
	@RequestMapping("/language/delete/{languageId}")
	public String deleteLanguage(@PathVariable Long languageId, RedirectAttributes attributes) {
		Language language = this.service.findById(languageId);
		if ( language != null ) {
			this.service.delete(language);
			attributes.addFlashAttribute("messages", new ArrayList<String>(Arrays.asList(String.format("%s has been deleted", language.getName()))));
		}
		return "redirect:/";
	}
	
	@RequestMapping("/language/edit/{languageId}")
	public String editLanguage(@PathVariable Long languageId, Model model) {
		Language language = this.service.findById(languageId);
		if( language != null ) {
			model.addAttribute("language", language);
			return "language/editLanguage.html";
		}
		return "redirect:/updateLanguage";
	}
	
	@RequestMapping(path="/language/update/{languageId}", method=RequestMethod.POST)
	public String updateLanguage(@PathVariable Long languageId, 
			@Valid Language language, BindingResult result, RedirectAttributes attributes){
		if( result.hasErrors() ) return String.format("redirect:/language/edit/%d", languageId);
		else {
			service.update(languageId, language);
			return "redirect:/";
		}
	}
}
