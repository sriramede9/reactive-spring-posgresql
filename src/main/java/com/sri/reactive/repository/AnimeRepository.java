package com.sri.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.sri.reactive.domain.Anime;

//@Repo

public interface AnimeRepository extends ReactiveCrudRepository<Anime, Integer> {

}
