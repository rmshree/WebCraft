package app.web.services;

import app.web.domain.Settings;
import app.web.domain.User;

public interface SettingsService {

    Settings save(Settings settings);

    Settings getByUser(User user);

    Settings findById(Integer id);

}
