package app.web.services;

import app.web.data.SettingsRepository;
import app.web.domain.Settings;
import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SettingsServiceImpl implements SettingsService{

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public Settings save(Settings settings){
        return settingsRepository.save(settings);
    }

    @Override
    public Settings getByUser(User user){
        return settingsRepository.getByUser(user.getUsername());
    }

    @Override
    public Settings findById(Integer id){
        return settingsRepository.findOne(id);
    }
}
