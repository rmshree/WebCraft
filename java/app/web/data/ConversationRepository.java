package app.web.data;

import app.web.domain.Conversation;
import app.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Integer>{

    @Query("select c from Conversation c where c.user1 = ?1 and c.user2 = ?2 or c.user1 = ?2 and c.user2 = ?1 ")
    Conversation getConvobyUsers(User user1, User user2);

    @Query("select c from Conversation c where c.user1 = ?1 or c.user2 = ?1")
    List<Conversation> getConvobyUser(User user);

    @Query("select c from Conversation c where c.id = ?1")
    Conversation getconvoByID(Integer id);
}
