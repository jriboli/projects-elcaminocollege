package pokemon.arena.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Weakness {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long weaknessId;
	
	@OneToOne
	@JoinColumn(name = "pokemon_id")
	private Pokemon pokemon;
	
	@ManyToOne
	@JoinColumn(name = "energy_id")
	private Energy energy;
	
	private double multiplier;
}
