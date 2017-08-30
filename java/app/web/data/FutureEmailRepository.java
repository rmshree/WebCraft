package app.web.data;

import app.web.domain.FutureEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FutureEmailRepository extends JpaRepository<FutureEmail, Integer>  {

    @Query("select  f from FutureEmail f where f.date <= ?1")
    List<FutureEmail> getExpired(Date date);

    @Modifying
    @Query("delete from FutureEmail f where f.id = ?1")
    void deleteFutureEmail(Integer id);
}