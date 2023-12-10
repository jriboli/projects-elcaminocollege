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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import pokemon.arena.enums.PokemonStatus;

@Entity
@Data
public class Pokemon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pokemonId;
	
	private int healthPoints;
	private String pokemonName;
	private String pokemonDescription;
	private PokemonStatus status;
	private String evolvesFrom;
	private int cardNumber;
	
	//ManyToOne energyType
	@ManyToOne
	@JoinColumn(name = "energy_id")
	private Energy energy;
	
	//ManyToMany actions
	@ManyToMany
	@JoinTable(
			name = "pokemon_action",
			joinColumns = @JoinColumn(name = "pokemon_id"),
			inverseJoinColumns = @JoinColumn(name = "action_id"))
	private List<Action> actions = new ArrayList<>();
	
	//OneToMany resistance
	@OneToOne(mappedBy = "pokemon")
	private Resistance resistance;
	
	//OneToMany weakness
	@OneToOne(mappedBy = "pokemon")
	private Weakness weakness;
}
