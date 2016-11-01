package app.web.controllers;

import app.web.domain.Containers.MatchContainer;
import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.User;
import app.web.services.MatchService;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/api/match/")
public class MatchController {

    @Autowired
    private UserService userService;

    @Autowired
    private MatchService matchService;

    //TODO: Create match database.
    @RequestMapping(value = "complete", method = RequestMethod.PUT)
    public ResponseDTO complete(@RequestBody MatchContainer matchContainer) {

        ResponseDTO responseDTO = new ResponseDTO();
        User user;
        ArrayList<User> winnerUsers = new ArrayList<>(0);
        ArrayList<User> loserUsers = new ArrayList<>(0);

        if (matchContainer.losers.length == 0 || matchContainer.winners.length == 0) {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Winners or losers have not been set.");
            return responseDTO;
        }

        for (String winner : matchContainer.winners) {
            if (winner.equals("")) break;

            user = userService.getUserByUsername(winner);
            if (user == null) {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("A user was not found.");
                return responseDTO;
            }
            winnerUsers.add(user);
        }
        for (String loser : matchContainer.losers) {
            if (loser.equals("")) break;

            user = userService.getUserByUsername(loser);
            if (user == null) {
                responseDTO.setSuccess(false);
                responseDTO.setMessage("A user was not found.");
                return responseDTO;
            }
            loserUsers.add(user);
        }

        return matchService.updateMatch(winnerUsers, loserUsers);
    }
}
