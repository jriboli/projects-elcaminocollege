package pokemon.arena.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playerId;
	
	private String playerName;
	
	// ManyToMany Pokemon
	@ManyToMany
	@JoinTable(
			name = "player_pokemon", 
			joinColumns = @JoinColumn(name = "player_id"),
			inverseJoinColumns = @JoinColumn(name = "pokemon_id"))
	private List<Pokemon> pokemons = new ArrayList<>();
}
