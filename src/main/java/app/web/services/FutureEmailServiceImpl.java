package app.web.services;

import app.web.data.FutureEmailRepository;
import app.web.domain.FutureEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FutureEmailServiceImpl implements FutureEmailService{

    @Autowired
    private FutureEmailRepository futureEmailRepository;

    @Override
    public FutureEmail save(FutureEmail futureEmail){
        return futureEmailRepository.save(futureEmail);
    }

    @Override
    public List<FutureEmail> getExpired(){
        Date now = new Date();
        return futureEmailRepository.getExpired(now);
    }
}
