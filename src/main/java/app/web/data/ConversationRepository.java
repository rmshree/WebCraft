package app.web.data;

import app.web.domain.Conversation;
import app.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, String>{
    @Query("select c from Conversation c where c.user1.username = ?1 and c.user2.username = ?2")
            Conversation getConvobyUsers(String user1, String user2);
}
