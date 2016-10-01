package app.web.data;

import app.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select currentUser from User currentUser where currentUser.age = ?1 and currentUser.name = ?2")
    List<User> getByAgeAndName(Integer age, String name);
}