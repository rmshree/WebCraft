package app.web.services;

import app.web.data.GameMapRepository;
import app.web.domain.GameMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameMapServiceImpl implements GameMapService {

    @Autowired
    private GameMapRepository gameMapRepository;

    @Override
    public GameMap save(GameMap gameMap){
        return gameMapRepository.save(gameMap);
    }
}
