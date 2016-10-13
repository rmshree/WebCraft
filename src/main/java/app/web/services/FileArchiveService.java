package app.web.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//returns pre-signed URL
public interface FileArchiveService {

    URL uploadFile(MultipartFile m_fileToUpload, String key) throws IOException;

    Object getFile(URL userURL) throws IOException;

}
