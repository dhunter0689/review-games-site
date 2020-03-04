package reviews;

import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GamePopulator implements CommandLineRunner {

	@Resource
	private GameRepository gameRepo;
	
	@Resource
	private GenreRepository genreRepo;
	
	@Resource
	private PlatformRepository platformRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		Genre action = new Genre ("Action");
		action = genreRepo.save(action);
		Genre adventure = new Genre ("Adventure");
		adventure = genreRepo.save(adventure);
		Genre sports = new Genre ("Sports");
		sports = genreRepo.save(sports);
		
		Game cyberpunk = new Game ("Cyber Punk", "An action role-playing game, set in Night City, California 2077", adventure);
		cyberpunk = gameRepo.save(cyberpunk);
		Game madden = new Game("Madden", "Build your Ultimate Madden Team, and play like an NFL superstar", sports);
		madden = gameRepo.save(madden);
		Game pubg = new Game("Player's Unknown Battlegrounds", "Be the last one standing in this Battle Royale", action);
		pubg = gameRepo.save(pubg);
		Game residentevil = new Game("Resident Evil", "Fight off mutated zombies and bring down the Umbrella Corporation", action);
		
		platformRepo.save(new Platform("Playstation 4", cyberpunk));
		platformRepo.save(new Platform("Playstation 4", madden));
		platformRepo.save(new Platform("Playstation 4", pubg));
		platformRepo.save(new Platform("Playstation 4", residentevil));
		
		platformRepo.save(new Platform("XBOX One", cyberpunk));
		platformRepo.save(new Platform("XBOX One", madden));
		platformRepo.save(new Platform("XBOX One", pubg));
		platformRepo.save(new Platform("XBOX One", residentevil));
		
		platformRepo.save(new Platform("PC", cyberpunk));
		platformRepo.save(new Platform("PC", pubg));

		
		platformRepo.save(new Platform("Mobile", pubg));
		platformRepo.save(new Platform("MObile", residentevil));



		
	}
	
}
