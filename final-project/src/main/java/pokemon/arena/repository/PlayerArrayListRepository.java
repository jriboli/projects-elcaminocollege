package pokemon.arena.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import pokemon.arena.models.PlayerData;

// Tell Spring to keep track of it 
// and give us one of this items when we ask for it
@Repository
public class PlayerArrayListRepository implements PlayerTestRepository{

	private ArrayList<PlayerData> players = new ArrayList<PlayerData>();
	
	public PlayerArrayListRepository() {
		players.add(new PlayerData((long) 1, "Rocket"));
		players.add(new PlayerData((long) 2, "Peter"));
		players.add(new PlayerData((long) 3, "Gamora"));
	}
	
	@Override
	public PlayerData[] all() {
		return (PlayerData[]) players.toArray(new PlayerData[0]);
	}

	@Override
	public PlayerData get(Long id) {
		return players.stream()
				.filter((player) -> player.getPlayerId().equals(id))
				.findFirst()
				.orElse(null);
	}

}
