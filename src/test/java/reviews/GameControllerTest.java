package reviews;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class GameControllerTest {
	
	@InjectMocks
	private GameController underTest;
	
	@Mock
	private Game game;
	
	@Mock
	private Game anotherGame;
	
	@Mock
	private Genre genre;
	
	@Mock
	private Genre anotherGenre;
	
	@Mock
	private GameRepository gameRepo;
	
	@Mock
	private GenreRepository genreRepo;
	
	
	@Mock
	private Model model;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleGameToModel() throws GameNotFoundException {
		long arbitraryGameId = 1;
		when(gameRepo.findById(arbitraryGameId)).thenReturn(Optional.of(game));
		
		underTest.findOneGame(arbitraryGameId, model);
		verify(model).addAttribute("games", game);
	}
	
	@Test
	public void shouldAddAllGamesToModel() {
		Collection<Game> allGames = Arrays.asList(game, anotherGame);
		when(gameRepo.findAll()).thenReturn(allGames);
		
		underTest.findAllGames(model);
		verify(model).addAttribute("games", allGames);
	}
	
	@Test
	public void shouldAddSingleGenreToModel()throws GenreNotFoundException {
		long arbitraryGenreId = 1;
		when(genreRepo.findById(arbitraryGenreId)).thenReturn(Optional.of(genre));
		
		underTest.findOneGenre(arbitraryGenreId, model);
		
		verify(model).addAttribute("genres", genre);
		
	}
	
	@Test
	public void shouldAddAllGenresToModel() {
		Collection<Genre> allGenres = Arrays.asList(genre, anotherGenre);
		when(genreRepo.findAll()).thenReturn(allGenres);
		
		underTest.findAllGenres(model);
		verify(model).addAttribute("genres", allGenres);
		
	}
	
	
	
	
	

}
