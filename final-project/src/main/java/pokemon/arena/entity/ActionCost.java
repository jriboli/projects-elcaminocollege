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
public class ActionCost {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actionCostId;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;

    @ManyToOne
    @JoinColumn(name = "energy_id")
    private Energy energy;

    private int cost;
}
