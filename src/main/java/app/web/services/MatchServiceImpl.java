package app.web.services;

import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class MatchServiceImpl implements MatchService{

    @Autowired
    UserService userService;

    //TODO: Add match history related code in this function. Potentially have separate functions.
    @Override
    public ResponseDTO updateMatch(ArrayList<User> winnersList, ArrayList<User> losersList){
        ResponseDTO responseDTO = new ResponseDTO();
        Integer averageWinnerELO = 0;
        Integer averageLoserELO = 0;
        Integer eloGain = 0;
        Integer eloLoss = 0;

        for (User winner: winnersList) {
            averageWinnerELO += winner.getElo();
        }
        averageWinnerELO = averageWinnerELO/winnersList.size();

        for (User loser: losersList) {
            averageLoserELO += loser.getElo();
        }
        averageLoserELO = averageLoserELO/losersList.size();

        for (User winner: winnersList) {
            eloGain = 15 + ((averageLoserELO - winner.getElo())/5);
            if (eloGain > 30) {
                eloGain = 30;
            } else if (eloGain < 1){
                eloGain = 1;
            }
            winner.setElo(winner.getElo() + eloGain);
            winner.setWin(winner.getWin() + 1);
            userService.save(winner);
        }

        for (User loser: losersList) {
            eloLoss = 15 - ((averageWinnerELO - loser.getElo())/5);
            if (eloLoss > 30) {
                eloLoss = 30;
            } else if (eloLoss < 1) {
                eloLoss = 1;
            }
            if (loser.getElo() - eloLoss >= 0) {
                loser.setElo(loser.getElo() - eloLoss);
            } else { //if < 0
                loser.setElo(0);
            }
            loser.setLoss(loser.getLoss() + 1);
            userService.save(loser);
        }

        responseDTO.setSuccess(true);
        responseDTO.setMessage("User match history and ranking has been updated.");
        return responseDTO;
    }
}
