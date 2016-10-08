package app.web.data;

import app.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username = ?1")
    User getUserByUsername (String username);

    @Query("select u from User u where u.email = ?1")
    User getUserByEmail (String email);

}