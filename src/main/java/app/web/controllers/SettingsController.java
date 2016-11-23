package app.web.controllers;


import app.web.domain.DTOs.ResponseDTO;
import app.web.domain.Settings;
import app.web.domain.User;
import app.web.services.SettingsService;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/settings/")
public class SettingsController {

    @Autowired
    private UserService userService;

    @Autowired
    private SettingsService settingsService;

    @RequestMapping(value = "user/{username}/{apiKey}", method = RequestMethod.GET)
    public ResponseDTO getUserSettings(@PathVariable String username, @PathVariable String apiKey) {
        User user = userService.getUserByUsername(username);
        ResponseDTO responseDTO = new ResponseDTO();
        if (apiKey.equals("Nitta160")) {
            if (user == null) {
                responseDTO.setData(null);
                responseDTO.setSuccess(false);
                responseDTO.setMessage("User does not exist.");
            } else {
                responseDTO.setData(settingsService.getByUser(user));
                responseDTO.setSuccess(true);
                responseDTO.setMessage("SUCCESS");
            }
        }
        else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Access denied.");
        }
        return responseDTO;
    }

    @RequestMapping(value = "update/{apiKey}", method = RequestMethod.PUT)
    public ResponseDTO update(@RequestBody Settings settings, @PathVariable String apiKey) {
        ResponseDTO responseDTO = new ResponseDTO();
        if (apiKey.equals("Nitta160")) {
            if (settingsService.findById(settings.getId()) != null) {
                responseDTO.setData(settingsService.save(settings));
                responseDTO.setSuccess(true);
                responseDTO.setMessage("SUCCESS");
            } else {
                // settings don't exist...
                responseDTO.setData(null);
                responseDTO.setSuccess(false);
                responseDTO.setMessage("User does not exist.");
            }
        }
        else {
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Access denied.");
        }
        return responseDTO;
    }
}
