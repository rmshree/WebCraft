package app.web.services;

import app.web.data.MessageRepository;
import app.web.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MessageServicelmpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message save(Message message){
        return messageRepository.save(message);
    }

    @Override
    public Message getMessageByID(Integer id){
        return messageRepository.getMessageByID(id);
    }

    @Override
    public List<Message> getMessageByConversation(Integer id){
        return messageRepository.getMessagesbyConversation(id);
    }

    @Override
    public List<Message> getMessagesByUsername(String username){
        return messageRepository.getMessagesByUsername(username);
    }
}
