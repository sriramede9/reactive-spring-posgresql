package com.sri.reactive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sri.reactive.domain.Anime;
import com.sri.reactive.repository.AnimeRepository;
import com.sri.reactive.service.AnimateService;
import com.sri.reactive.util.AnimeCreator;

import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class AnimeServiceTest {

	@InjectMocks
	private AnimateService animeService;

	@Mock
	private AnimeRepository animeRepository;

	private final Anime anime = AnimeCreator.createAnimeToBeSaved();
	Anime animeById = AnimeCreator.createAnimeToBeValidated();

//	@Test
//	public void getTEst() {
//		assertEquals("a", "a");
//	}

//	@BeforeAll
//	public static void blockHoundSetup() {
//		
//		
//	}

	@BeforeEach
	public void SetUp() {
		BDDMockito.when(animeRepository.findAll()).thenReturn(Flux.just(new Anime(1, "Sri")));
		BDDMockito.when(animeRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Mono.just(animeById));
	}

	@Test
	public void Test_all_Flux() {

		StepVerifier.create(animeService.findAllAnime()).expectSubscription().expectNext(new Anime(1, "Sri"))
				.expectComplete();
	}

	@Test
	public void FindById_test() {
	
		StepVerifier.create(animeService.findAnimeById(1))
		.expectSubscription()
		.expectNext(animeById)
		.verifyComplete();
	}
}
