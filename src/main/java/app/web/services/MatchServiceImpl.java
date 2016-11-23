package app.web.services;

import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.MatchPlayer;
import app.web.domain.MatchResult;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.Math.*;

import java.util.ArrayList;

@Service
@Transactional
public class MatchServiceImpl implements MatchService{

    @Autowired
    private UserService userService;

    @Autowired
    private MatchPlayerService matchPlayerService;

    private final static Integer KFACTOR = 28;
    private final static Double MINIMUMGAINLOSS = 1.0;

    //TODO: Add match history related code in this function. Potentially have separate functions.
    @Override
    public ResponseDTO updateMatch(MatchResult matchResult, ArrayList<User> winnersList, ArrayList<User> losersList){
        ResponseDTO responseDTO = new ResponseDTO();
        Double averageWinnerELO = 0.0;
        Double averageLoserELO = 0.0;
        Double expectedGain;
        Double expectedLoss;

        for (User winner: winnersList) {
            averageWinnerELO += winner.getElo();
        }
        averageWinnerELO = averageWinnerELO/winnersList.size();
        averageWinnerELO = Math.pow(10, (averageWinnerELO/400));

        for (User loser: losersList) {
            averageLoserELO += loser.getElo();
        }
        averageLoserELO = averageLoserELO/losersList.size();
        averageLoserELO = Math.pow(10, (averageLoserELO/400));

        for (User winner: winnersList) {
            expectedGain = Math.pow(10, (winner.getElo()/400))/(Math.pow(10, (winner.getElo()/400)) + averageLoserELO);
            expectedGain = KFACTOR *((1 - expectedGain) + 0.03 *(losersList.size() - winnersList.size()));
            if (expectedGain < 1) {
                expectedGain = MINIMUMGAINLOSS;
            }
            MatchPlayer matchPlayer = new MatchPlayer();
            matchPlayer.setMatchResult(matchResult);
            matchPlayer.setWon(true);
            matchPlayer.setElo(winner.getElo());
            matchPlayer.setUser(winner);
            matchPlayerService.save(matchPlayer);

            winner.setElo(winner.getElo() + expectedGain.intValue());
            winner.setWin(winner.getWin() + 1);
            userService.save(winner);
        }

        for (User loser: losersList) {
            expectedLoss = Math.pow(10, (loser.getElo()/400))/(Math.pow(10, (loser.getElo()/400)) + averageWinnerELO);
            expectedLoss = KFACTOR * ((expectedLoss) + 0.03 * (losersList.size() - winnersList.size()));
            if (expectedLoss < 1 ) {
                expectedLoss = 1 * MINIMUMGAINLOSS;
            }
            MatchPlayer matchPlayer = new MatchPlayer();
            matchPlayer.setMatchResult(matchResult);
            matchPlayer.setWon(false);
            matchPlayer.setElo(loser.getElo());
            matchPlayer.setUser(loser);
            matchPlayerService.save(matchPlayer);

            if (loser.getElo() - expectedLoss.intValue() > 1)
            {
                loser.setElo(loser.getElo() - expectedLoss.intValue());
            }
            else {
                loser.setElo(1);
            }
            loser.setLoss(loser.getLoss() + 1);
            userService.save(loser);
        }

        responseDTO.setSuccess(true);
        responseDTO.setMessage("User match history and ranking has been updated.");
        return responseDTO;
    }
}
