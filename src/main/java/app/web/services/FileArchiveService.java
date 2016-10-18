package app.web.services;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//returns pre-signed URL
public interface FileArchiveService {

    String upload(MultipartFile m_fileToUpload, String key, ObjectMetadata meta) throws IOException;

    Object getUrlContent(String url) throws IOException;

    void delete(String key);

}
