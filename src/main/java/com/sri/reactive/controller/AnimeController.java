package com.sri.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sri.reactive.domain.Anime;
import com.sri.reactive.repository.AnimeRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class AnimeController {

	private final AnimeRepository animeRepository;

	@GetMapping("all")
	public Flux<Anime> getAllAnime() {
		Flux<Anime> findAll = animeRepository.findAll();
		return findAll;
	}
}
