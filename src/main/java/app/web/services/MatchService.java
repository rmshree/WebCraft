package app.web.services;

import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.User;

import java.util.ArrayList;

public interface MatchService {

    ResponseDTO updateMatch(ArrayList<User> winnersList, ArrayList<User> losersList);
}
