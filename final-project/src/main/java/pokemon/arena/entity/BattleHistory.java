package pokemon.arena.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class BattleHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long battleHistoryId;
	
	@ManyToOne
	@JoinColumn(name = "battle_id")
	private Battle battle;
	
	private String actionBy;
	private String actionDescription; 
}
