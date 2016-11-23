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

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "get/conversation/{apiKey}", method = RequestMethod.GET)
    public Conversation getConvoByUsers(@PathVariable String apiKey, @RequestBody User user1, @RequestBody User user2) {
        Conversation convo = conversationService.getConvobyUsers(user1, user2);
        if (convo == null || !(apiKey.equals("Nitta160"))) {
            return null;
        } else {
            return convo;
        }

    }

    @RequestMapping(value = "get/conversation/{id}/{apiKey}", method = RequestMethod.GET)
    public Conversation getConvoByID(@PathVariable Integer id, @PathVariable String apiKey) {
        Conversation convo = conversationService.getConvobyID(id);
        if (convo == null || !(apiKey.equals("Nitta160"))) {
            return null;
        } else {
            return convo;
        }

    }

    @RequestMapping(value = "get/conversations/{username}/{apiKey}", method = RequestMethod.GET)
    public List<Conversation> getConvoByUser(@PathVariable String username, @PathVariable String apiKey) {
        User user = userService.getUserByUsername(username);
        if (apiKey.equals("Nitta160")) {
            return conversationService.getConvobyUser(user);
        }
        return null;
    }

    @RequestMapping(value = "save/conversation/{apiKey}", method = RequestMethod.POST)
    public Conversation saveConversation(@RequestBody Conversation conversation, @PathVariable String apiKey) {
        Conversation convo = conversationService.save(conversation);
        if (convo == null || !(apiKey.equals("Nitta160"))) {
            return null;
        } else {
            return convo;
        }

    }


    @RequestMapping(value = "get/message/{id}/{apiKey}", method = RequestMethod.GET)
    public Message getMessagebyID(@PathVariable Integer id, @PathVariable String apiKey) {
        Message msg = messageService.getMessageByID(id);
        if (msg == null || !(apiKey.equals("Nitta160"))) {
            return null;
        } else {
            return msg;
        }
    }


    @RequestMapping(value = "save/{apiKey}", method = RequestMethod.POST)
    public Message saveMessage(@RequestBody Message message, @PathVariable String apiKey) {
        if (apiKey.equals("Nitta160")) {
        Date now = new Date();
        Conversation conversation = conversationService.getConvobyUsers(message.getReceiver(), message.getSender());
        if (conversation == null) {
            Conversation newConversation = new Conversation();
            newConversation.setUser1(message.getReceiver());
            newConversation.setUser2(message.getSender());
            newConversation.setLastMessageTime(now);
            newConversation.setLastMessage(message.getMessage_body());
            newConversation = conversationService.save(newConversation);
            message.setConvo_id(newConversation.getId());
        } else {
            message.setConvo_id(conversation.getId());
            conversation.setLastMessageTime(now);
            conversation.setLastMessage(message.getMessage_body());
            conversationService.save(conversation);
        }

        Settings settings = settingsService.getByUser(message.getReceiver());
        if (settings != null && settings.getNotifications()) {
            String content = "Dear " + message.getReceiver().getUsername() + ",\n This notification is to inform you that " + message.getSender().getUsername() + " has sent you a message on Nittacraft.\n";
            if (settings.getDelay() == 0) {
                // send an immediate
                emailService.sendNewMessageEmail(message.getReceiver(), content);

            } else {
                FutureEmail futureEmail = new FutureEmail();
                futureEmail.setUser(message.getReceiver());
                futureEmail.setContent(content);
                Date futureTime = new Date();
                switch (settings.getDelayUnit()) {
                    case "Minutes":
                        futureTime.setTime(futureTime.getTime() + 60000 * settings.getDelay());
                        break;
                    case "Hours":
                        futureTime.setTime(futureTime.getTime() + 60 * 60000 * settings.getDelay());
                        break;
                    case "Days":
                        futureTime.setTime(futureTime.getTime() + 24 * 60 * 60000 * settings.getDelay());
                        break;
                    default:
                        break;
                }
                futureEmail.setDate(futureTime);
                futureEmailService.save(futureEmail);
            }
        }
        message.setCreateDate(now);
        return messageService.save(message);
        }
        return null;
    }

    @RequestMapping(value = "get/messages/{id}/{apiKey}", method = RequestMethod.GET)
    public List<Message> getMessagesByConvoID(@PathVariable Integer id, @PathVariable String apiKey) {
        if (apiKey.equals("Nitta160")) {
            return messageService.getMessageByConversation(id);
        }
        return null;
    }

    @RequestMapping(value = "user/{username}/{apiKey}", method = RequestMethod.GET)
    public List<Message> getMessagesByUsers(@PathVariable String username, @PathVariable String apiKey) {
        User user = userService.getUserByUsername(username);
        if (user != null || (apiKey.equals("Nitta160"))) {
            return messageService.getMessagesByUsername(username);
        } else {
            return null;
        }
    }
}