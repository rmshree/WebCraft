package app.web.services;

import app.web.data.FutureEmailRepository;
import app.web.domain.FutureEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FutureEmailServiceImpl implements FutureEmailService{

    @Autowired
    private FutureEmailRepository futureEmailRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public FutureEmail save(FutureEmail futureEmail){
        return futureEmailRepository.save(futureEmail);
    }

    @Override
    public List<FutureEmail> getExpired(){
        Date now = new Date();
        return futureEmailRepository.getExpired(now);
    }

    @Override
    @Scheduled(fixedRate = 30000)
    public void sendEmails(){
        List<FutureEmail> all = getExpired();
        for(FutureEmail email : all){
            emailService.sendNewMessageEmail(email.getUser(), email.getContent());
            delete(email);
        }
    }

    private void delete(FutureEmail futureEmail){
        futureEmailRepository.deleteFutureEmail(futureEmail.getId());
    }
}
