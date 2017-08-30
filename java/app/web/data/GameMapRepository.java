package app.web.data;

import app.web.domain.GameMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameMapRepository extends JpaRepository<GameMap, String>{

    @Query("select m from GameMap m where m.title = ?1")
    GameMap findByTitle(String title);

}
