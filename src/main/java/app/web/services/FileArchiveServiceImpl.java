package app.web.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
@Transactional
public class FileArchiveServiceImpl implements FileArchiveService {

    private static final String S3_BUCKET_NAME = "ecs160-bucket";

    @Override
    public URL uploadFile(MultipartFile m_fileToUpload, String key) throws IOException {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJOEDY645IHM7PJOA", "Mw0Mn4QpX6NgMmTR6FJq79UoVKHCN7h1yAcgXZsC");

        AmazonS3 s3Client = new AmazonS3Client(awsCreds);
        // save file
        File fileToUpload = multipartToFile(m_fileToUpload);
        s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, fileToUpload));

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(S3_BUCKET_NAME, key);
        generatePresignedUrlRequest.setMethod(HttpMethod.GET);
        return (s3Client.generatePresignedUrl(generatePresignedUrlRequest));
    }

    private File multipartToFile(MultipartFile file) throws IOException
    {
        File convFile = new File(file.getOriginalFilename());
        if(convFile.createNewFile() ) {
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        }
        return convFile;
    }
    @Override
    //return file specified under given URL
    public Object getFile(URL userURL) throws IOException {
        return (userURL.getContent() );

    }

}