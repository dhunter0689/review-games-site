package reviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Game {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String description;

	@ManyToMany
	private Collection<Genre> genres;
	
	@OneToMany(mappedBy = "game")
	private Collection<Platform> platforms;
	
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Game() {
	}
	
	public Game (String name, String description, Genre...genres) {
		this.name = name;
		this.description = description;
		this.genres = new HashSet<>(Arrays.asList(genres));
	}

	public Collection<Genre> getGenres() {
		
		return genres;
	}

	public Collection<Platform> getPlatforms() {
		
		return platforms;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (id != other.id)
			return false;
		return true;
	}

	


}
