package app.web.services;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

//returns pre-signed URL
public interface FileArchiveService {

    URL uploadFile(MultipartFile m_fileToUpload, String key) throws IOException;

    String uploadFile2(MultipartFile m_fileToUpload, String key, ObjectMetadata meta) throws IOException;

    Object getFile(URL userURL) throws IOException;

}
