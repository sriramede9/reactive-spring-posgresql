package com.sri.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.sri.reactive.domain.Anime;

import reactor.core.publisher.Mono;

//@Repo

public interface AnimeRepository extends ReactiveCrudRepository<Anime, Integer> {

	Mono<Anime> findById(Integer id);

}
