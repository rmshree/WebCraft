package app.web.controllers;

import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.GameMap;
import app.web.services.FileArchiveService;
import app.web.services.GameMapService;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/map/")
public class GameMapController {

    @Autowired
    private GameMapService gameMapService;

    @Autowired
    private FileArchiveService fileArchiveService;

    @RequestMapping(value = "save/{apiKey}", method = RequestMethod.PUT)
    public ResponseDTO save(@PathVariable String apiKey, @RequestBody GameMap gameMap) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (apiKey.equals("Nitta160")) {
        if (gameMapService.findByTitle(gameMap.getTitle()) == null) {
            responseDTO.setData(gameMapService.save(gameMap));
            responseDTO.setSuccess(true);
            responseDTO.setMessage("SUCCESS");
        } else {
            // title taken
            responseDTO.setData(null);
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Map title is already taken. Please change the title and try again");
        }
        }
        else {
            responseDTO.setData(null);
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Access denied");
        }

        return responseDTO;
    }

    @RequestMapping(value = "{id}/upload/file/{apiKey}", method = RequestMethod.POST)
    public GameMap uploadFile(@PathVariable String id, @PathVariable String apiKey, MultipartFile file) {
        GameMap gameMap = gameMapService.findById(id);
        if (apiKey.equals("Nitta160")) {
            try {
                String key = "maps/" + gameMap.getTitle();
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType("text/plain");
                gameMap.setDownloadUrl(fileArchiveService.upload(file, key, objectMetadata));
            } catch (Exception e) {
                e.printStackTrace();
            }
            // need to save the map and get url for it.
            return gameMapService.save(gameMap);
        }
        return null;
    }

    @RequestMapping(value = "{id}/{apiKey}", method = RequestMethod.GET)
    public ResponseDTO get(@PathVariable String id,@PathVariable String apiKey) {
        ResponseDTO responseDTO = new ResponseDTO();
        GameMap gameMap = gameMapService.findById(id);
        if (apiKey.equals("Nitta160")) {
            if (gameMap != null) {
                responseDTO.setData(gameMap);
                responseDTO.setMessage("SUCCESS");
                responseDTO.setSuccess(true);
            } else {
                responseDTO.setMessage("Unknown map");
                responseDTO.setSuccess(false);
            }
        }
        else {
            responseDTO.setMessage("Access denied");
            responseDTO.setSuccess(false);
        }
        return responseDTO;
    }

    @RequestMapping(value = "{id}/upload/primary/{apiKey}", method = RequestMethod.POST)
    public GameMap uploadPrimaryImage(@PathVariable String id, @PathVariable String apiKey, MultipartFile file) {
        GameMap gameMap = gameMapService.findById(id);
        if (apiKey.equals("Nitta160")) {
            try {
                String key = "maps/images/" + gameMap.getTitle();
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentType("img/jpeg");
                gameMap.setPrimaryImageUrl(fileArchiveService.upload(file, key, objectMetadata));
            } catch (Exception e) {
                e.printStackTrace();
            }
            // need to save the primary image in AWS and get url for it.
            return gameMapService.save(gameMap);
        }
        return null;
    }

    @RequestMapping(value = "all/{apiKey}", method = RequestMethod.GET)
    public List<GameMap> all(@PathVariable String apiKey) {
        if (apiKey.equals("Nitta160")) {
            return gameMapService.getAll();
        }
        return null;
    }

    @RequestMapping(value = "download/{id}/{apiKey}", method = RequestMethod.GET)
    public ResponseDTO download(@PathVariable String id, @PathVariable String apiKey) {
        ResponseDTO responseDTO = new ResponseDTO();
        GameMap gameMap = gameMapService.findById(id);
        if (apiKey.equals("Nitta160")) {
            if (gameMap != null) {
                gameMap.setCount(gameMap.getCount() + 1);
                responseDTO.setData(gameMapService.save(gameMap));
                responseDTO.setMessage("SUCCESS");
                responseDTO.setSuccess(true);
            } else {
                responseDTO.setSuccess(false);
                responseDTO.setData(null);
                responseDTO.setMessage("Unknown Map. Map does not exist");
            }
        }
        else {
            responseDTO.setMessage("Access denied.");
            responseDTO.setSuccess(false);
            responseDTO.setData(null);
        }

        return responseDTO;
    }
}
