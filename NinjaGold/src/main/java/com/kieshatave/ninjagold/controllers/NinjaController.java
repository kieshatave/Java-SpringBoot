package com.kieshatave.ninjagold.controllers;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
public class NinjaController {
	@RequestMapping("/")
	public String index(HttpSession session, Model model) {
		ArrayList<String> activities = new ArrayList<String>();
		
		session.setAttribute("gold", 0);
		model.addAttribute("gold", 0);
	
		session.setAttribute("activities", activities);
		model.addAttribute("activities", activities);
		
		return "home/index.html";
	}
	
	@PostMapping(value="/process")
	public String process(@RequestParam(value="location") String location, HttpSession session, Model model) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		Random rand = new Random();
		ArrayList<String> activities = (ArrayList<String>) session.getAttribute("activities");
		
		if(location.equals("farm")) {
			int num = rand.nextInt((20-10) + 1) + 10;
			int gold = (int) session.getAttribute("gold");
			session.setAttribute("gold", gold + num);
			model.addAttribute("gold", gold + num);
			activities.add("You enterted farm and earned " + num + "gold. (" + timeStamp + ")");
			model.addAttribute("activities", activities);
		}
		else if(location.equals("cave")) {
			int num = rand.nextInt((10-5) + 1) + 5;
			int gold = (int) session.getAttribute("gold");
			session.setAttribute("gold", gold + num);
			model.addAttribute("gold", gold + num);
			activities.add("You enterted cave and earned " + num + "gold. (" + timeStamp + ")");
			model.addAttribute("activities", activities);
		}
		else if(location.equals("house")) {
			int num = rand.nextInt((5-2) + 1) + 2;
			int gold = (int) session.getAttribute("gold");
			session.setAttribute("gold", gold + num);
			model.addAttribute("gold", gold + num);
			activities.add("You enterted house and earned " + num + "gold. (" + timeStamp + ")");
			model.addAttribute("activities", activities);
		}
		else if(location.equals("casino")) {
			int num = rand.nextInt((50-50) + 1) + 50;
			int gold = (int) session.getAttribute("gold");
			session.setAttribute("gold", gold + num);
			model.addAttribute("gold", gold + num);
			if(num > 0 ) {
				activities.add("You enterted casino and earned " + num + "gold. (" + timeStamp + ")");
				model.addAttribute("activities", activities);
			} 
			activities.add("You enterted casino and lost " + num + "gold. (" + timeStamp + ")");
			model.addAttribute("activities", activities);
		}
		return "redirect:/";
		
	}
}
