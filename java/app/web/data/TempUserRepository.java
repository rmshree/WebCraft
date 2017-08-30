package app.web.data;

import app.web.domain.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TempUserRepository extends JpaRepository<TempUser, String> {

    @Query("select u from TempUser u where u.verificationKey = ?1")
    TempUser getUserByVerificationKey(String verificationKey);

    @Query("select u from TempUser u where u.username = ?1")
    TempUser getUserByUsername (String username);

    @Query("select u from TempUser u where u.email = ?1")
    TempUser getUserByEmail (String email);

    @Modifying
    @Query("delete from TempUser u where u.verificationKey = ?1")
    Integer deleteTempUser(String verificationKey);

}
