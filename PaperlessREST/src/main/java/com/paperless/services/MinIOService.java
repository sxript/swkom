package com.paperless.services;

import org.springframework.web.multipart.MultipartFile;

public interface MinIOService {
    boolean uploadDocument(MultipartFile documentFile, String path);
}
