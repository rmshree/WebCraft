package app.web.services;

import app.web.domain.FutureEmail;

import java.util.List;

public interface FutureEmailService {

    FutureEmail save(FutureEmail futureEmail);

    List<FutureEmail> getExpired();

    void sendEmails();
}
