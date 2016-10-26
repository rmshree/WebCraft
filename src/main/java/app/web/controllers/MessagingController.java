package app.web.controllers;

import app.web.domain.*;
import app.web.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/messages/")
public class MessagingController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private UserService userService;

    @Autowired
    private FutureEmailService futureEmailService;

    @Autowired
    private SettingsService settingsService;


    @RequestMapping(value = "get/conversation", method = RequestMethod.GET)
    public Conversation getConvoByUsers(@RequestBody User user1, @RequestBody User user2) {
        Conversation convo = conversationService.getConvobyUsers(user1, user2);
        if (convo == null) {
            return null;
        } else {
            return convo;
        }

    }

    @RequestMapping(value = "get/conversation/{id}", method = RequestMethod.GET)
    public Conversation getConvoByID(@PathVariable Integer id) {
        Conversation convo = conversationService.getConvobyID(id);
        if (convo == null) {
            return null;
        } else {
            return convo;
        }

    }

    @RequestMapping(value = "get/conversations/{username}", method = RequestMethod.GET)
    public List<Conversation> getConvoByUser(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return conversationService.getConvobyUser(user);

    }

    @RequestMapping(value = "save/conversation", method = RequestMethod.POST)
    public Conversation saveConversation(@RequestBody Conversation conversation) {
        Conversation convo = conversationService.save(conversation);
        if (convo == null) {
            return null;
        } else {
            return convo;
        }

    }


    @RequestMapping(value = "get/message/{id}", method = RequestMethod.GET)
    public Message getMessagebyID(@PathVariable Integer id) {
        Message msg = messageService.getMessageByID(id);
        if (msg == null) {
            return null;
        } else {
            return msg;
        }
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Message saveMessage(@RequestBody Message message) {
        Conversation conversation = conversationService.getConvobyUsers(message.getReceiver(), message.getSender());
        if (conversation == null) {
            Conversation newConversation = new Conversation();
            newConversation.setUser1(message.getReceiver());
            newConversation.setUser2(message.getSender());
            newConversation = conversationService.save(newConversation);
            message.setConvo_id(newConversation.getId());
        } else {
            message.setConvo_id(conversation.getId());
        }

        Settings settings = settingsService.getByUser(message.getReceiver());
        Date now = new Date();

        if(settings.getDelay() == 0){
            // send an immediate
        } else {
            FutureEmail futureEmail = new FutureEmail();
            futureEmail.setUser(message.getReceiver());
            String content = "Dear " + message.getReceiver().getUsername() + ",\n This notification is to inform you that " + message.getSender().getUsername() + " has sent you a message on Nittacraft.\n";
            futureEmail.setContent(content);
            Date fututeTime = new Date();
            if(settings.getDelayUnit().equals("Minutes")){
                fututeTime.setTime(fututeTime.getTime() + 60000 * settings.getDelay());
            }else if(settings.getDelayUnit().equals("Hours")){
                fututeTime.setTime(fututeTime.getTime() + 60 * 60000 * settings.getDelay());
            }else if(settings.getDelayUnit().equals("Days")){
                fututeTime.setTime(fututeTime.getTime() + 24 * 60 * 60000 * settings.getDelay());
            }else{

            }
            futureEmail.setDate(fututeTime);
            futureEmailService.save(futureEmail);
        }
        message.setCreateDate(now);
        return messageService.save(message);
    }

    @RequestMapping(value = "get/messages/{id}", method = RequestMethod.GET)
    public List<Message> getMessagesByConvoID(@PathVariable Integer id) {
        return messageService.getMessageByConversation(id);
    }

    @RequestMapping(value = "user/{username}", method = RequestMethod.GET)
    public List<Message> getMessagesByUsers(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return messageService.getMessagesByUsername(username);
        } else {
            return null;
        }
    }
}