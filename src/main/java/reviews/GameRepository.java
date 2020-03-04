package reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

	Collection<Game> findByGenresContains(Genre action);

	Collection<Game> findByGenresId(Long id);
}
