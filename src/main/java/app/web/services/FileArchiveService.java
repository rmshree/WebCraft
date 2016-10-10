package app.web.services;

import app.web.domain.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//returns pre-signed URL
public interface FileArchiveService {
    URL uploadImage(File fileToUpload);

    Object getUserImage(User user) throws IOException;

}
