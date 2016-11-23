package app.web.controllers;

import app.web.domain.Containers.MatchContainer;
import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.MatchPlayer;
import app.web.domain.MatchResult;
import app.web.domain.User;
import app.web.services.MatchPlayerService;
import app.web.services.MatchResultService;
import app.web.services.MatchService;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/api/match/")
public class MatchController {

    @Autowired
    private UserService userService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchPlayerService matchPlayerService;

    @Autowired
    private MatchResultService matchResultService;

    //TODO: Create match database.
    @RequestMapping(value = "complete", method = RequestMethod.PUT)
    public ResponseDTO complete(@RequestBody MatchContainer matchContainer) {

        ResponseDTO responseDTO = new ResponseDTO();
        User user;
        MatchResult matchResult = new MatchResult();
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
        matchResult.setLosers(loserUsers);
        matchResult.setWinners(winnerUsers);
        if (matchContainer.mapname != null) {
            matchResult.setMapname(matchContainer.mapname);
        }
        matchResultService.save(matchResult);

        return matchService.updateMatch(matchResult, winnerUsers, loserUsers);
    }

    @RequestMapping(value = "matchhistory/{username}", method = RequestMethod.GET)
    public ResponseDTO getMatchHistory(@PathVariable String username) {
        ResponseDTO responseDTO = new ResponseDTO();
        User requestUser = userService.getUserByUsername(username);
        if (requestUser != null) {
            List<MatchPlayer> matchPlayerList =  matchPlayerService.matchResultReturnContainerList(requestUser);
            responseDTO.setSuccess(true);
            responseDTO.setData(matchPlayerList);
        }
        else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("User does not exist.");
        }
        return responseDTO;
    }
}
