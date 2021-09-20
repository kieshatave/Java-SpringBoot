package com.kieshatave.lookify.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.kieshatave.lookify.models.Song;

public interface SongRepository extends CrudRepository<Song, Long>{
	List<Song> findByArtist(String artist);
	List<Song> findTop10ByOrderByRatingDesc();
}
