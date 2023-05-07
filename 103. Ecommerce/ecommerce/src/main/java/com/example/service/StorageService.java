package com.example.service;

import com.example.exception.StorageException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

    private final Path rootLocation;

    public StorageService() {
        this.rootLocation = Paths.get("uploads");
    }

    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = StringUtils.getFilenameExtension(filename);
        String justFilename = filename.replace("." + extension, "");
        String storedFilename = System.currentTimeMillis() + "_" + justFilename + "." + extension;

        try {

            if (file.isEmpty()) throw new StorageException("Failed to store empty file " + filename);
            if (filename.contains("..")) throw new StorageException("Cannot store outside directory" + filename);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(
                        inputStream,
                        this.rootLocation.resolve(storedFilename),
                        StandardCopyOption.REPLACE_EXISTING
                );
                return storedFilename;
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) throw new StorageException("Could not read file: " + filename);
            return resource;
        } catch (MalformedURLException e) {
            throw new StorageException("Could not read file: " + filename, e);
        }
    }
}

