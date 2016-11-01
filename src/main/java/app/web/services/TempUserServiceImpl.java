package app.web.services;

import app.web.data.TempUserRepository;
import app.web.domain.TempUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TempUserServiceImpl implements  TempUserService{

    @Autowired
    private TempUserRepository tempUserRepository;

    public TempUser save(TempUser tempUser) {
        return tempUserRepository.save(tempUser);
    }

    public TempUser getTempUserByVerificationKey(String verificationKey) {
        return tempUserRepository.getUserByVerificationKey(verificationKey);
    }

    public TempUser getTempUserByUsername (String username) {
        return tempUserRepository.getUserByUsername(username);
    }

    public TempUser getTempUserByEmail (String email) {
        return tempUserRepository.getUserByEmail(email);
    }

    public Integer deleteTempUser(TempUser tempUser) {
        return tempUserRepository.deleteTempUser(tempUser.getVerificationKey());
    }
}
