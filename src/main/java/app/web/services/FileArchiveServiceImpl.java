package app.web.services;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.FileOutputStream;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class FileArchiveServiceImpl implements FileArchiveService {
    @Autowired
    private AmazonS3Client s3Client;
    private static final String S3_BUCKET_NAME = "ecs160-bucket";


    public URL uploadFile(MultipartFile m_fileToUpload, String key) throws IOException
    {
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

    //return file specified under given URL
    public Object getFile(URL userURL) throws IOException {
        return (userURL.getContent() );

    }

}