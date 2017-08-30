package app.web.services;

import app.web.data.MatchPlayerRepository;
import app.web.data.MatchResultRepository;
import app.web.domain.MatchPlayer;
import app.web.domain.MatchResult;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MatchPlayerServiceImpl implements MatchPlayerService{

    @Autowired
    MatchPlayerRepository matchPlayerRepository;

    @Autowired
    MatchResultRepository matchResultRepository;

    @Override
    public MatchPlayer save(MatchPlayer matchPlayer) {
        return matchPlayerRepository.save(matchPlayer);
    }

    @Override
    public List<MatchPlayer> matchResultReturnContainerList(User user) {
        List<MatchPlayer> matchPlayerList = matchPlayerRepository.getPlayerHistory(user);
        MatchResult matchResult;
        return matchPlayerList;
    }
}
