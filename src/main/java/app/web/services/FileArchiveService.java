package app.web.services;

import app.web.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//returns pre-signed URL
public interface FileArchiveService {

    URL uploadImage(MultipartFile m_fileToUpload, String key) throws IOException;

    Object getUserImage(User user) throws IOException;

    File multipartToFile(MultipartFile file) throws IOException;

}
