package app.web.services;
import app.web.domain.Conversation;
import app.web.domain.User;

public interface ConversationService {

    Conversation save(Conversation conversation);

    Conversation getConvobyUsers(User user1, User user2);

    Conversation getConvobyUser(User user);

    Conversation getConvobyID(Integer id);

}
