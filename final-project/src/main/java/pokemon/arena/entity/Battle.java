package pokemon.arena.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import pokemon.arena.enums.BattleStatus;

@Entity
@Data
public class Battle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long battleId;

	private int randomPokemonHealthPoint;
	private BattleStatus status;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	@ManyToOne
	@JoinColumn(name = "random_pokemon_id")
	private Pokemon randomPokemon;
	
	//OneToMany History
	@OneToMany(mappedBy = "battle")
	private List<BattleHistory> battleHistory = new ArrayList<>();
}
