package app.web.services;

import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.MatchResult;
import app.web.domain.User;

import java.util.ArrayList;

public interface MatchService {

    ResponseDTO updateMatch(MatchResult matchResult, ArrayList<User> winnersList, ArrayList<User> losersList);
}
