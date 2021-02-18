package com.sri.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.sri.reactive.domain.Anime;
import com.sri.reactive.exception.AnimeNotfoundException;
import com.sri.reactive.repository.AnimeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AnimateService {

	@Autowired
	private final AnimeRepository animeRepository;

	public Flux<Anime> findAllAnime() {
		return animeRepository.findAll();
	}

	public Mono<Anime> findAnimeById(Integer id) {
		return animeRepository.findById(id);
	}

	public Mono<Anime> addAnimeUser(Anime anime) {

		Mono<Anime> save = animeRepository.save(anime.setAsNew());

		return save;
	}

	@Transactional
	public Mono<Anime> updateAnimeUser(Anime anime) {

		return animeRepository.findById(anime.getId()).flatMap(animedb -> {
			animedb.setName(anime.getName());
			return animeRepository.save(animedb);
		}).switchIfEmpty(Mono.error(new AnimeNotfoundException("No anime found to update")));

	}

	public Mono<Anime> updateAnimeUserTESt(Mono<Anime> findAnimeById) {

		return findAnimeById.flatMap(animeRepository::save);
	}

//	@Transactional
	public Mono<Void> deleteAnimeById(Integer id) {

		return animeRepository.findById(id).flatMap(animeRepository::delete);

	}

}
