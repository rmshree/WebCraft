package app.web.data;
import app.web.domain.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("select m from Message m where m.id = ?1")
    Message getMessageByID(Integer id);

    @Query("select m from Message m where m.convo_id = ?1")
    List <Message> getMessagesbyConversation(Integer id);

    @Query("select m from Message m where m.sender.username = ?1 or m.receiver.username = ?1")
    List <Message> getMessagesByUsername(String username);

}
