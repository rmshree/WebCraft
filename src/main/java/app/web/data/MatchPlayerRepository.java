package app.web.data;

import app.web.domain.MatchPlayer;
import app.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchPlayerRepository extends JpaRepository<MatchPlayer, Integer> {

    @Query("select p from MatchPlayer p where p.user =?1")
    List<MatchPlayer> getPlayerHistory(User user);
}
