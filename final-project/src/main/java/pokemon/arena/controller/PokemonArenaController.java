package pokemon.arena.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pokemon.arena.models.PlayerData;
import pokemon.arena.repository.PlayerTestRepository;

@RestController
@RequestMapping("/pokemon-arena")
@Slf4j
public class PokemonArenaController {
	private PlayerTestRepository repository;
	
	// Constructor Injections
	public PokemonArenaController(PlayerTestRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/players")
	public PlayerData[] getPlayers() {
		log.info("Getting all Players");
		return repository.all();
	}
	
	@GetMapping("/players/{playerId}")
	public PlayerData getPlayerById(@PathVariable Long playerId) {
		log.info("Getting Player with ID={}", playerId);
		return repository.get(playerId);
	}
	
	
}
