package app.web.services;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;

import app.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class FileArchiveServiceImpl {
    @Autowired
    private AmazonS3Client s3Client;
    private static final String S3_BUCKET_NAME = "ecs160-bucket";

    //Save image to S3 and return user public URL
    public URL uploadImage(File fileToUpload) {
        //string consists of file name + timestamp
        String key = Instant.now().getEpochSecond() + "_" + fileToUpload.getName();

        // save file
        s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, fileToUpload));

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(S3_BUCKET_NAME, key);
        generatePresignedUrlRequest.setMethod(HttpMethod.GET);
        URL signedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        return signedUrl;
    }

    //return object (image) specified under given URL by given user
    public Object getUserImage(User user) throws IOException {
        URL userURL = user.getUserURL();
        return (userURL.getContent() );

    }

}