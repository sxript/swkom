package com.paperless.services;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@Slf4j
public class MinIOService {

    @Value("${minio.bucketName}")
    @Getter
    private String bucketName;

    private final MinioClient minioClient;

    @Autowired
    public MinIOService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }


    private void checkAndCreateBucket() {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                // if bucket does not exist, create it
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                System.out.println("Bucket created successfully");
            } else {
                System.out.println("Bucket " + bucketName + "already exists");
            }
        } catch (Exception e) {
            System.out.println("Bucket does not exist and could not create bucket"+ e);
        }
    }

    public void uploadDocument(MultipartFile documentFile, String path) {
        checkAndCreateBucket();

        try {
            // Upload file to MinIO
            InputStream inputStream = documentFile.getInputStream();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                            .stream(documentFile.getInputStream(), documentFile.getSize(), -1) // size is the size of the InputStream, -1 indicates unknown size
                            .contentType(documentFile.getContentType())
                            .build()
            );
            System.out.println("Document uploaded to MinIO.");
        } catch (Exception e) {
            System.out.println("Error uploading document to MinIO: {}" + e.getMessage());
            throw new RuntimeException("Error uploading document to MinIO", e);
        }

    }

}
