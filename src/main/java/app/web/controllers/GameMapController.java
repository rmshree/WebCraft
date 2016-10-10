package app.web.controllers;

import app.web.domain.GameMap;
import app.web.services.GameMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/map/")
public class GameMapController {

    @Autowired
    private GameMapService gameMapService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public GameMap save(@RequestBody GameMap gameMap, MultipartFile file){
        return gameMapService.save(gameMap);
    }

}
