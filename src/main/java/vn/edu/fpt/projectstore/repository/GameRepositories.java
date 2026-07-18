package vn.edu.fpt.projectstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.projectstore.entity.Game;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepositories extends JpaRepository<Game,String> {
    Optional<Game> findByGameId(UUID id);


    Game findByGameName(String name);



}
