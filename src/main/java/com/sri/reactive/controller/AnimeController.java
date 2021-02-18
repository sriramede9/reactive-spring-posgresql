package com.sri.reactive.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;

import com.sri.reactive.domain.Anime;
import com.sri.reactive.exception.AnimeNotfoundException;
import com.sri.reactive.repository.AnimeRepository;
import com.sri.reactive.service.AnimateService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AnimeController {

	@Autowired
	private final AnimateService animeService;

	@GetMapping("all")
	public Flux<Anime> getAllAnime() {
		Flux<Anime> findAll = animeService.findAllAnime();
		return findAll;
	}

	@GetMapping("/id/{id}")
	public Mono<Anime> getAnimeById(@PathVariable("id") Integer id) {
		Mono<Anime> findAnimeById = animeService.findAnimeById(id);
		
		findAnimeById.subscribe(System.out::println);
		
		return findAnimeById.switchIfEmpty(Mono.error(new AnimeNotfoundException("No Anime with give id \t" + id)));
//				.log();
	}

	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Anime> addAnimeUser(@RequestBody @Valid Anime anime) {
		Mono<Anime> addAnimeUser = animeService.addAnimeUser(anime);
		return addAnimeUser;
	}

	@PutMapping("/id/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Mono<Anime> updateAnimeUser(@PathVariable("id") Integer id, @RequestBody @Valid Anime anime) {
		
//		Anime withId = anime.withId(id);
//		System.out.println(withId);
		
		Mono<Anime> updateAnimeUser = animeService.updateAnimeUser(anime);
		return updateAnimeUser.doOnError(Throwable::getLocalizedMessage);
	}
	
	@DeleteMapping("/id/{id}")
	public Mono<Void> DeleteAnimeById(@PathVariable("id") Integer id) {
		Mono<Anime> findAnimeById = animeService.findAnimeById(id);
		
//		findAnimeById.subscribe(System.out::println);
		
		return animeService.deleteAnimeById(id);
		
//		return findAnimeById.switchIfEmpty(Mono.error(new AnimeNotfoundException("No Anime with give id \t" + id)));
//				.log();
	}

}
