package pokemon.arena.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Energy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long energyId;
	
	private String energyName;
	
	//OneToMany Pokemon
	@OneToMany(mappedBy = "energy")
	private List<Pokemon> pokemons = new ArrayList<>();
	
	//OneToMany Resistance
	@OneToMany(mappedBy = "energy")
	private List<Resistance> resistances = new ArrayList<>();
	
	//ManyToMany Weakness
	@OneToMany(mappedBy = "energy")
	private List<Weakness> weaknesses = new ArrayList<>();
	
	//ManyToMany Action
	@OneToMany(mappedBy = "energy")
	private List<ActionCost> actionCosts = new ArrayList<>();
}
