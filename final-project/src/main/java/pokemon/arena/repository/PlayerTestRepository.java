package pokemon.arena.repository;

import pokemon.arena.models.PlayerData;

public interface PlayerTestRepository {

	/**
	 * Returns all users.
	 * @return The users in the system. If none, then an empty list is returned.
	 */
	PlayerData[] all();
	
	/**
	 * Retireves a user by it's unique id.
	 * @param id The uniue id of the user.
	 * @return The user if found, otherwise null.
	 */
	PlayerData get(Long id);
}
