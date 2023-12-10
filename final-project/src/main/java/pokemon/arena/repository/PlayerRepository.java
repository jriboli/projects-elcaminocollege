package pokemon.arena.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pokemon.arena.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
