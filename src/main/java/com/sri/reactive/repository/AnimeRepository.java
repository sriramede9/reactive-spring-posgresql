package com.sri.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.sri.reactive.domain.Anime;

import reactor.core.publisher.Mono;

//@Repo
@Repository
public interface AnimeRepository extends ReactiveCrudRepository<Anime, Integer> {

	Mono<Anime> findById(Integer id);

}
