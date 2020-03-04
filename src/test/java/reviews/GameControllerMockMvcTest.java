package reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerMockMvcTest {

	@Resource
	private MockMvc mvc;

	@MockBean
	private GameRepository gameRepo;

	@MockBean
	private GenreRepository genreRepo;

	@Mock
	private Game game;

	@Mock
	private Game anotherGame;
	
	@Mock
	private Genre genre;
	
	@Mock
	private Genre anotherGenre;

	@Test
	public void shouldRouteToSingleGameView() throws Exception {
		long arbitraryGameId = 1;
		when(gameRepo.findById(arbitraryGameId)).thenReturn(Optional.of(game));
		mvc.perform(get("/game?id=1")).andExpect(view().name(is("game")));
	}

	@Test
	public void shouldBeOkForSingleGame() throws Exception {

		long arbitraryGameId = 1;
		when(gameRepo.findById(arbitraryGameId)).thenReturn(Optional.of(game));
		mvc.perform(get("/game?id=1")).andExpect(status().isOk());

	}

	@Test
	public void shouldNotBeOkForSingleGame() throws Exception {

		mvc.perform(get("/game?id=1")).andExpect(status().isNotFound());

	}

	@Test
	public void shouldPutSingleGameIntoModel() throws Exception {
		when(gameRepo.findById(1L)).thenReturn(Optional.of(game));

		mvc.perform(get("/game?id=1")).andExpect(model().attribute("games", is(game)));
	}

	@Test
	public void shouldRouteToAllGamesView() throws Exception {
		mvc.perform(get("/show-games")).andExpect(view().name(is("games")));
	}

	@Test
	public void shouldBeOkForAllGames() throws Exception {
		mvc.perform(get("/show-games")).andExpect(status().isOk());

	}

	@Test
	public void shouldPutAllGamesIntoModel() throws Exception {
		Collection<Game> allGames = Arrays.asList(game, anotherGame);
		
		when(gameRepo.findAll()).thenReturn(allGames);
		
		mvc.perform(get("/show-games")).andExpect(model().attribute("games", is(allGames)));
	}
	
	
	@Test
	public void shouldRouteToSingleGenreView() throws Exception {
		long arbitraryGenreId = 15;
		when(genreRepo.findById(arbitraryGenreId)).thenReturn(Optional.of(genre));
		mvc.perform(get("/genre?id=15")).andExpect(view().name(is("genre")));
	}

	@Test
	public void shouldBeOkForSingleGenre() throws Exception {

		long arbitraryGenreId = 15;
		when(genreRepo.findById(arbitraryGenreId)).thenReturn(Optional.of(genre));
		mvc.perform(get("/genre?id=15")).andExpect(status().isOk());

	}

	@Test
	public void shouldNotBeOkForSingleGenre() throws Exception {

		mvc.perform(get("/genre?id=15")).andExpect(status().isNotFound());

	}

	@Test
	public void shouldPutSingleGenreIntoModel() throws Exception {
		when(genreRepo.findById(15L)).thenReturn(Optional.of(genre));

		mvc.perform(get("/genre?id=15")).andExpect(model().attribute("genres", is(genre)));
	}


	@Test
	public void shouldBeOkForAllGenres() throws Exception {
		mvc.perform(get("/genres")).andExpect(status().isOk());

	}

	@Test
	public void shouldRouteToAllGenresView() throws Exception {
		Collection<Genre> allGenres = Arrays.asList(genre, anotherGenre);
		
		when(genreRepo.findAll()).thenReturn(allGenres);
		
		mvc.perform(get("/genres")).andExpect(model().attribute("genres", is(allGenres)));
	}

}
