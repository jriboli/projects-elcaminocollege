package pokemon.arena.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long actionId;
	
	private String actionName;
	private int actionDamage;
	private String actionAttribute;
	
	// OneToMany energy
	@OneToMany(mappedBy = "action")
	private List<ActionCost> actionCosts = new ArrayList<>();
	
	// ManyToMany pokemon
	@ManyToMany(mappedBy = "actions")
	private List<Pokemon> pokemons = new ArrayList<>();
}
