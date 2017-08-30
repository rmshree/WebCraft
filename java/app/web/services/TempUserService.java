package app.web.services;

import app.web.domain.TempUser;

public interface TempUserService {

    TempUser save(TempUser tempUser);

    TempUser getTempUserByVerificationKey(String verificationKey);

    TempUser getTempUserByUsername (String username);

    TempUser getTempUserByEmail (String email);

    Integer deleteTempUser(TempUser tempUser);
}
