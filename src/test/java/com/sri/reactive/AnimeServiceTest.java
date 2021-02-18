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
import com.sri.reactive.exception.AnimeNotfoundException;
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
		BDDMockito.when(animeRepository.save(anime)).thenReturn(Mono.just(animeById));
		BDDMockito.when(animeRepository.deleteById(ArgumentMatchers.anyInt())).thenReturn(Mono.empty());
	}

	@Test
	public void Test_all_Flux() {

		StepVerifier.create(animeService.findAllAnime()).expectSubscription().expectNext(new Anime(1, "Sri"))
				.expectComplete();
	}

	@Test
	public void FindById_Success_test() {

		StepVerifier.create(animeService.findAnimeById(1)).expectSubscription().expectNext(animeById).verifyComplete();
	}

	@Test
	public void FindById_Error_test() {

		BDDMockito.when(animeRepository.findById(ArgumentMatchers.anyInt()))
				.thenReturn(Mono.error(new AnimeNotfoundException("no anime of given id")));

		StepVerifier.create(animeService.findAnimeById(1)).expectSubscription()
				.expectErrorMessage("no anime of given id").verify();
	}

	@Test
	public void saveAnime_test() {

		StepVerifier.create(animeService.addAnimeUser(anime)).expectSubscription().expectNext(animeById)
				.verifyComplete();
	}

	@Test
	public void deleteAnime_test() {

		StepVerifier.create(animeService.deleteAnimeById(1)).expectSubscription().expectComplete();
	}

	@Test
	public void deleteAnime_Error_test() {
		BDDMockito.when(animeRepository.deleteById(ArgumentMatchers.anyInt()))
				.thenReturn(Mono.error(new AnimeNotfoundException("no anime found to delete")));

		StepVerifier.create(animeService.deleteAnimeById(1)).expectSubscription()
		.expectError(AnimeNotfoundException.class);
	}
	@Test
	public void UpdateAnime_Success_test() {
		BDDMockito.when(animeRepository.save(animeById)).thenReturn(Mono.empty());

		StepVerifier.create(animeService.updateAnimeUser(animeById)).expectSubscription();
	}
}
