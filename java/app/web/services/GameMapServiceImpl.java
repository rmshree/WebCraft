package app.web.services;

import app.web.data.GameMapRepository;
import app.web.domain.GameMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameMapServiceImpl implements GameMapService {

    @Autowired
    private GameMapRepository gameMapRepository;

    @Override
    public GameMap save(GameMap gameMap) {
        return gameMapRepository.save(gameMap);
    }

    @Override
    public List<GameMap> getAll() {
        return gameMapRepository.findAll();
    }

    @Override
    public GameMap findById(String id) {
        return gameMapRepository.findOne(id);
    }

    @Override
    public GameMap findByTitle(String title) {
        return gameMapRepository.findByTitle(title);
    }
}
