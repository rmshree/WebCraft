package app.web.services;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
@Transactional
public class FileArchiveServiceImpl implements FileArchiveService {

    private static final String S3_BUCKET_NAME = "ecs160-bucket";
    private static final String ACCESSKEY = "AKIAJOEDY645IHM7PJOA";
    private static final String SECRETKEY = "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";


    @Override
    public String upload(MultipartFile m_fileToUpload, String key, ObjectMetadata meta) throws IOException {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(ACCESSKEY, SECRETKEY);

        AmazonS3 s3Client = new AmazonS3Client(awsCreds);

        InputStream fileIs = m_fileToUpload.getInputStream();
        s3Client.putObject(S3_BUCKET_NAME, key, fileIs, meta);
        s3Client.setObjectAcl(S3_BUCKET_NAME, key, CannedAccessControlList.PublicRead);

        //public URL getUrl(String bucketName, String key)
        URL returnedURL = s3Client.getUrl(S3_BUCKET_NAME, key);
        return (returnedURL.toString());

    }

    @Override
    public Object getUrlContent(String url) throws IOException{
        URL userURL = new URL(url);
        return userURL.getContent();
    }

    @Override
    public void delete(String key){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(ACCESSKEY, SECRETKEY);

        AmazonS3 s3Client = new AmazonS3Client(awsCreds);
        s3Client.deleteObject(S3_BUCKET_NAME, key);
    }


}