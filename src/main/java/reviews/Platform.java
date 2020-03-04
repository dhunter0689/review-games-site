package reviews;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Platform {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	@ManyToOne
	private Game game;
	
	
	public Platform () {
		
	}
	
	public Platform(String name, Game game) {
		this.name = name;
		this.game = game;
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
		Platform other = (Platform) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	


}
