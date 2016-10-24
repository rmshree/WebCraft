/** \file MessagingController.java
 * Back-End Messaging services that are used by Front-End.
 * Called by using /api/messaging/
 */

package app.web.controllers;
import app.web.services.ConversationService;
import app.web.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/messaging/")
public class MessagingController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ConversationService conversationService;

}
