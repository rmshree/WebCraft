package app.web.data;

import app.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    /** User getUserByUsername (String username);
     *  \brief Get User data from database
     *  \param username is a String.
     *  \return a user or NULL
     */
    @Query("select u from User u where u.username = ?1")
    User getUserByUsername (String username);

    /** User getUserByEmail (String email);
     *  \brief Get User data from database
     *  \param email is a String.
     *  \return a user or NULL
     */
    @Query("select u from User u where u.email = ?1")
    User getUserByEmail (String email);

    @Query("select u from User u where u.userKey = ?1")
    User getUserByUserKey(String userkey);

    @Query("select u from User u where u.isCurrentlyOnsite = true")
    List<User> getOnsiteUsers();

    @Query("select u from User u where u.isCurrentlyOnline = true")
    List<User> getOnlineUsers();
}