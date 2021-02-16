package com.sri.reactive.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sri.reactive.domain.Anime;
import com.sri.reactive.repository.AnimeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnimateService {

	private final AnimeRepository animeRepository;

	public Flux<Anime> findAllAnime() {
		return animeRepository.findAll();
	}
	
	public Mono<Anime> findAnimeById(Integer id) {
		return animeRepository.findById(id);
	}

	public Mono<Anime> addAnimeUser(Anime anime) {
		
		// TODO Auto-generated method stub
		
//		Mono<Boolean> existsById = animeRepository.existsById(anime.getId());
		
		
		
		Mono<Anime> save = animeRepository.save(anime);
		
		return save;
	}

}
