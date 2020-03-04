package reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

	@Resource
	GameRepository gameRepo;
	
	@Resource
	GenreRepository genreRepo;

	@RequestMapping("/game")
	public String findOneGame(@RequestParam(value = "id") long id, Model model) throws GameNotFoundException {
		Optional<Game> game = gameRepo.findById(id);

		if (game.isPresent()) {
			model.addAttribute("games", game.get());
			return "game";
		}
		throw new GameNotFoundException();
	}

	@RequestMapping("/show-games")
	public String findAllGames(Model model) {
		model.addAttribute("games", gameRepo.findAll());
		return ("games");

	}

	@RequestMapping("/genre")
	public String findOneGenre(@RequestParam(value= "id") long id, Model model) throws GenreNotFoundException {
		Optional<Genre> genre = genreRepo.findById(id);

		if (genre.isPresent()) {
			model.addAttribute("genres", genre.get());
			model.addAttribute("games", gameRepo.findByGenresContains(genre.get()));
			return "genre";
		}
		throw new GenreNotFoundException();
	}
	
	@RequestMapping("/genres")
	public String findAllGenres(Model model) {
		model.addAttribute("genres", genreRepo.findAll());
		return ("genres");

	}
	

}
