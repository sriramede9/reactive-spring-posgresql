package com.sri.reactive.util;

import com.sri.reactive.domain.Anime;

public class AnimeCreator {

	public static Anime createAnimeToBeSaved() {

		return Anime.builder().name("Sri").build();
	}

	public static Anime createAnimeToBeValidated() {

		return Anime.builder().id(1).name("Sri").build();
	}

	public static Anime createAnimeToBeupdated() {

		return Anime.builder().id(1).name("Sri ede").build();
	}
}
