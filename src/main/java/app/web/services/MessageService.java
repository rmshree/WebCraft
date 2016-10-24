package app.web.services;

import app.web.domain.Message;

import java.util.List;

public interface MessageService {
    Message save(Message message);

    Message getMessagetByID(Integer id);

    List<Message> getMessageByConvo(Integer id);

}
