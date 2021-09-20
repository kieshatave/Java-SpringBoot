package com.kieshatave.lookify.services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.kieshatave.lookify.models.Song;
import com.kieshatave.lookify.repositories.SongRepository;

@Service
public class SongService {
	private final SongRepository repo;
	
	public SongService(SongRepository repo) {
		this.repo = repo;
	}
	
	public void addSong(Song song) {
		repo.save(song);
	}

	public List<Song> findAllSongs() {
		return (List<Song>) repo.findAll();
	}

	public Song addSong(Long myId) {
		Optional<Song> findSong = repo.findById(myId);
		if (findSong.isPresent()) {
			return findSong.get();
		} else {
			return null;
		}
	}
	
	public Song findSong(Long id) {
		Optional<Song> song = this.repo.findById(id);
		if(song.isPresent()) {
			return song.get();
		} else {
			return null;
		}
	}

	public void deleteSong(Long id) {
		repo.deleteById(id);
	}
	
	public List<Song> findbyArtist(String name){
		return repo.findByArtist(name);
	}
	
	public List<Song> getTopTen() {
		return repo.findTop10ByOrderByRatingDesc();
	}
	
}
