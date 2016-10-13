package app.web.controllers;

import app.web.domain.GameMap;
import app.web.services.FileArchiveService;
import app.web.services.GameMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping(value = "/api/map/")
public class GameMapController {

    @Autowired
    private GameMapService gameMapService;

    @Autowired
    private FileArchiveService fileArchiveService;

    @RequestMapping(value = "save", method = RequestMethod.PUT)
    public GameMap save(@RequestBody GameMap gameMap){
        return gameMapService.save(gameMap);
    }

    @RequestMapping(value = "{id}/upload/file", method = RequestMethod.POST)
    public GameMap uploadFile(@PathVariable String id, MultipartFile file){
        GameMap gameMap = gameMapService.findById(id);
        try{
            URL url = fileArchiveService.uploadFile(file, gameMap.getId());
            gameMap.setDownloadUrl(url.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        // need to save the map and get url for it.
        return gameMapService.save(gameMap);
    }

    @RequestMapping(value = "{id}/upload/primary", method = RequestMethod.POST)
    public GameMap uploadPrimaryImage(@PathVariable String id, MultipartFile file){
        GameMap gameMap = gameMapService.findById(id);
        // need to save the primary image in AWS and get url for it.
        return gameMapService.save(gameMap);
    }

    @RequestMapping(value = "{id}/upload/secondary", method = RequestMethod.POST)
    public GameMap uploadSecondaryImage(@PathVariable String id, MultipartFile file){
        GameMap gameMap = gameMapService.findById(id);
        // need to save the secondary image in AWS and get url for it.
        return gameMapService.save(gameMap);
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<GameMap> all(){
        return gameMapService.getAll();
    }

    @RequestMapping(value = "download/{id}", method = RequestMethod.GET)
    public GameMap download(@PathVariable String id){
        GameMap gameMap = gameMapService.findById(id);
        gameMap.setCount(gameMap.getCount() + 1);
        return gameMapService.save(gameMap);
    }
}
