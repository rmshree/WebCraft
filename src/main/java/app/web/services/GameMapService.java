package app.web.services;

import app.web.domain.GameMap;

import java.util.List;

public interface GameMapService {

    GameMap save(GameMap gameMap);

    List<GameMap> getAll();

    GameMap findById(String id);

    GameMap findByTitle(String title);
}
