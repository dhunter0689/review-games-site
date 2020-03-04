package reviews;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//runs test
@DataJpaTest
public class JPAMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;
	@Resource
	private GenreRepository genreRepo;
	
	@Resource
	private GameRepository gameRepo;
	
	@Resource
	private PlatformRepository platformRepo;
	
	@Test
	public void shouldSaveAndLoadGenre() {
		Genre genre = genreRepo.save(new Genre("genre"));
		long genreId = genre.getId();
		
		entityManager.flush();//forces jpa to hit the database when we try to find it
		entityManager.clear();
		
		Optional<Genre> result = genreRepo.findById(genreId);
		genre =result.get();
		assertThat(genre.getName(), is("genre"));
	}
	
	@Test
	public void shouldGenerateGenreId() {
		Genre genre = genreRepo.save(new Genre("genre"));
		long genreId = genre.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(genreId, is(greaterThan(0L)));
		
	}
	
	@Test
	public void shouldSaveandLoadGame() {
		Game game = new Game ("game name", "description");
		game = gameRepo.save(game);
		long gameId = game.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Game> result = gameRepo.findById(gameId);
		game = result.get();
		assertThat(game.getName(), is("game name"));
		
	}
	
	@Test
	public void shouldEstablishGameToGenreRelationship() {
		//genre is not the owner so create genres first to be in the Game table
		Genre survival = genreRepo.save(new Genre("Survival"));
		Genre action = genreRepo.save(new Genre("Action"));
		
		Game game = new Game ("Resident Evil", "description", survival, action);
		game = gameRepo.save(game);
		long gameId = game.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Game> result = gameRepo.findById(gameId);
		result.get();
		assertThat(game.getGenres(), containsInAnyOrder(survival, action));
		
	}
	
	@Test 
	public void shouldFindGameForGenre() {//Test for query
		Genre action = genreRepo.save(new Genre("action"));
		
		Game PUBG = gameRepo.save(new Game("PUBG", "description", action));
		Game ResidentEvil = gameRepo.save(new Game("Resident Evil", "description", action));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Game> gamesForGenre = gameRepo.findByGenresContains(action);
		
		assertThat(gamesForGenre, containsInAnyOrder(PUBG, ResidentEvil));
		
	}
	
	@Test
	public void shouldFindGamesForGenreId() {
		Genre action = genreRepo.save(new Genre("action"));
		long genreId = action.getId();
		
		Game PUBG = gameRepo.save(new Game("PUBG", "description", action));
		Game ResidentEvil = gameRepo.save(new Game("Resident Evil", "description", action));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Game> gamesForGenre = gameRepo.findByGenresId(genreId);
		
		assertThat(gamesForGenre, containsInAnyOrder(PUBG, ResidentEvil));
		
	}
	
	@Test
	public void shouldSavePlatformToGameRelationship() {
		Game game = new  Game("name", "description");
		gameRepo.save(game);
		long gameId= game.getId();
		
		Platform console = new Platform("name", game);
		platformRepo.save(console);
		
		Platform console2 = new Platform("name two", game);
		platformRepo.save(console2);
	
	
	entityManager.flush();
	entityManager.clear();
	
	Optional<Game>result = gameRepo.findById(gameId);
	game = result.get();
	assertThat(game.getPlatforms(), contains(console, console2));
	
	}

}
