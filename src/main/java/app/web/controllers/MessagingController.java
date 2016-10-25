package app.web.controllers;

import app.web.domain.Message;
import app.web.domain.Conversation;
import app.web.domain.User;
import app.web.services.MessageService;
import app.web.services.ConversationService;
import app.web.services.UserService;
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

        message.setCreateDate(new Date());
        return messageService.save(message);
    }

    @RequestMapping(value = "get/messages/{conversation_id}", method = RequestMethod.GET)
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