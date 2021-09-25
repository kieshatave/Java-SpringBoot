package com.kieshatave.authentication.controllers;

import java.util.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kieshatave.authentication.models.User;
import com.kieshatave.authentication.services.UserService;

@Controller
public class UserController {
	private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/")
    public String index() {
    	return "redirect:/register";
    }
    
    @GetMapping("/register")
    public String registerForm(@ModelAttribute("user") User user) {
        return "user/registrationPage.html";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("user", new User());
        return "user/loginPage.html";
    }
    
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, 
    		BindingResult result, HttpSession session,
    		RedirectAttributes redirectAttributes) {
    	if (result.hasErrors()) return "registrationPage";
		user = this.userService.registerUser(user);
		session.setAttribute("userId", user.getId());
		return "redirect:/home";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, 
    		@RequestParam("password") String password, Model model, 
    		HttpSession session) {
    	boolean isAuthenticated = userService.authenticateUser(email, password);
		if(isAuthenticated) {
			User user = userService.findByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/home";
		}
		else {
			model.addAttribute("error", "Invalid Credentials! Please try again!");
			return "user/loginPage.html";	
		}
    }
    
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
		User user = userService.findUserById(userId);
		model.addAttribute("user", user);
		return "user/home.html";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
		return "redirect:/login";
    }
    
}
