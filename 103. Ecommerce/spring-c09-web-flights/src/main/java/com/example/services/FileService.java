package com.example.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String storeInFileSystem(MultipartFile file);

    Resource loadFile(String fileName);
}
