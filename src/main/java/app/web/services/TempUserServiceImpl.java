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

    @Override
    public TempUser save(TempUser tempUser) {
        return tempUserRepository.save(tempUser);
    }

    @Override
    public TempUser getTempUserByVerificationKey(String verificationKey) {
        return tempUserRepository.getUserByVerificationKey(verificationKey);
    }

    @Override
    public TempUser getTempUserByUsername (String username) {
        return tempUserRepository.getUserByUsername(username);
    }

    @Override
    public TempUser getTempUserByEmail (String email) {
        return tempUserRepository.getUserByEmail(email);
    }

    @Override
    public Integer deleteTempUser(TempUser tempUser) {
        return tempUserRepository.deleteTempUser(tempUser.getVerificationKey());
    }
}
