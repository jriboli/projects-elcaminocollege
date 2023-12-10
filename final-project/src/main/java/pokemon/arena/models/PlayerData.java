package pokemon.arena.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerData {
	private Long playerId;
	private String playerName;
	
	public PlayerData(Long id, String name) {
		this.playerId = id;
		this.playerName = name;
	}
}
