package com.kieshatave.lookify.controller;

import java.util.*;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kieshatave.lookify.models.Song;
import com.kieshatave.lookify.services.SongService;

@Controller
@RequestMapping("/")
public class SongController {
	private final SongService service;
	
	public SongController(SongService service) {
		this.service = service;
	}
	
	@RequestMapping("")
	public String index(){
		return "song/index.html";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model, @ModelAttribute("song") Song song) {
		List<Song> songs = service.findAllSongs();
		model.addAttribute("songs",songs);
		return "song/dashboard.html";
	}
	
	@RequestMapping("/song/{songId}")
	public String songDetail(Model model, @PathVariable("songId") Long songId) {
		Song song = (Song) service.findSong(songId);
		model.addAttribute("song", song);
		return "song/details.html";
	}
	
	@PostMapping("/search")
	public String search(@RequestParam("artist") String artist) {
		return "redirect:/search/" + artist;
	}
	
	@RequestMapping("/search/{artist}")
	public String searchResults(Model model, @PathVariable("artist") String artist) {
		List<Song> songs = service.findbyArtist(artist);
		model.addAttribute("artist", artist);
		model.addAttribute("songs", songs);
		return "song/search.html";
	}
	
	@RequestMapping("/search/topTen")
	public String showTopSongs(Model model) {
		List<Song> songs = service.getTopTen();
		model.addAttribute("songs", songs);
		return "song/topTen.html";
	}
	
	@RequestMapping("/song/add")
	public String getAddSong(Model model,
			@ModelAttribute("song") Song song) {
		return "song/addSong.html";
	}
	
	@PostMapping("/song/add")
	public String addSong(
			@Valid Song song,
			BindingResult result,
			RedirectAttributes errors) {
		if(result.hasErrors()) return "song/addSong.html";
			service.addSong(song);
			return "redirect:/dashboard";
	}
	
	@RequestMapping("/song/delete/{songId}")
	public String deleteSong(@PathVariable("songId") Long songId) {
		service.deleteSong(songId);
		return "redirect:/dashboard";
	}
	
	
}
