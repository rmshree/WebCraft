package app.web.controllers;

import app.web.domain.Message;
import app.web.domain.Conversation;
import app.web.domain.User;
import app.web.services.MessageService;
import app.web.services.ConversationService;
import app.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/messages/")
public class MessagingController {
    @Autowired
    private ConversationService convoService;


    @RequestMapping(value= "get/conversation", method = RequestMethod.GET)
    public Conversation getConvoByUsers(@RequestBody User user1, @RequestBody User user2){
        Conversation convo = convoService.getConvobyUsers(user1, user2);
        if(convo == null){
            return null;
        }
        else{
            return convo;
        }

    }

    @RequestMapping(value= "get/conversation/{id}", method = RequestMethod.GET)
    public Conversation getConvoByID(@PathVariable Integer id){
        Conversation convo = convoService.getConvobyID(id);
        if(convo == null){
            return null;
        }
        else{
            return convo;
        }

    }


}
