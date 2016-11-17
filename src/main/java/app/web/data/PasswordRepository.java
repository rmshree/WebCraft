package app.web.data;

import app.web.domain.Password;
import app.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordRepository extends JpaRepository<Password, Integer>{

    @Query("select p from Password p where p.user =?1")
    Password getPasswordByUser(User user);
}
