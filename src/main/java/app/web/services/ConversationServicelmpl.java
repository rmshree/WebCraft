package app.web.services;
import app.web.data.ConversationRepository;
import app.web.domain.Conversation;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConversationServicelmpl implements ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public Conversation save(Conversation conversation){
        return conversationRepository.save(conversation);
    }

    @Override
    public Conversation getConvobyUsers(User user1, User user2){
        return conversationRepository.getConvobyUsers(user1, user2);
    }

    @Override
    public Conversation getConvobyUser(User user){
        return conversationRepository.getConvobyUser(user);
    }

    @Override
    public Conversation getConvobyID(Integer id){
        return conversationRepository.getconvoByID(id);

    }

}
