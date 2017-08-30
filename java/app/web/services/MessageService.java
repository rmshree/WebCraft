package app.web.services;

import app.web.domain.Message;

import java.util.List;

public interface MessageService {
    Message save(Message message);

    Message getMessageByID(Integer id);

    List<Message> getMessageByConversation(Integer id);

    List<Message> getMessagesByUsername(String username);

}
