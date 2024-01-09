package com.paperless.services.impl;

import com.paperless.services.MinIOService;
import io.minio.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@Slf4j
public class MinIOServiceImpl implements MinIOService {

    @Value("${minio.bucketName}")
    @Getter
    private String bucketName;

    private final MinioClient minioClient;

    @Autowired
    public MinIOServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }


    private boolean checkAndCreateBucket() {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                log.info("Bucket created successfully");
            } else {
                log.info("Bucket " + bucketName + "already exists");
            }
            return true;
        } catch (Exception e) {
            log.error("Bucket does not exist and could not create bucket" + e);
        }

        return false;
    }

    @Override
    public boolean uploadDocument(MultipartFile documentFile, String path) {

        if (!checkAndCreateBucket()) return false;
        try {

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                            .stream(documentFile.getInputStream(), documentFile.getSize(), -1) // size is the size of the InputStream, -1 indicates unknown size
                            .contentType(documentFile.getContentType())
                            .build()
            );
            log.info("Document uploaded to MinIO.");
            return true;
        } catch (Exception e) {
            log.error("Error uploading document to MinIO: {}", e.getMessage());
            return false;
        }
    }

    public InputStream getObjectById(String id) throws RuntimeException {
        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(id)
                            .build()
            );
            System.out.println("Document downloaded from MinIO.");
            return stream;
        } catch (Exception e) {
            throw new RuntimeException("Error downloading document from MinIO", e);
        }
    }

}
