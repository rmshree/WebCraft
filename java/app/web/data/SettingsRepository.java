package app.web.data;

import app.web.domain.Settings;
import app.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SettingsRepository extends JpaRepository<Settings, Integer>  {

   @Query("select s from Settings s where s.user.username = ?1")
   Settings getByUser(String username);
}
