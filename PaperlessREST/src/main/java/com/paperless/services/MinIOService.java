package com.paperless.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface MinIOService {
    boolean uploadDocument(MultipartFile documentFile, String path);
    InputStream getObjectById(String id);
}
