package app.web.services;

import app.web.domain.MatchPlayer;
import app.web.domain.User;

import java.util.List;

public interface MatchPlayerService {

    MatchPlayer save(MatchPlayer matchPlayer);

    List<MatchPlayer> matchResultReturnContainerList(User user);

}
