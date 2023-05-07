package com.example.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileServiceImpl implements FileService {

    @Value("${app.img.location}")
    String imgsLocation;

    @Override
    public String storeInFileSystem(MultipartFile file) {

        if(file == null
                || !StringUtils.hasLength(file.getOriginalFilename())
                || StringUtils.getFilenameExtension(file.getOriginalFilename()) == null)
            throw new IllegalArgumentException("Imagen incorrecta");

        String fileNameWithExtension = file.getOriginalFilename(); // logo_impresionante.jpg
        String extension = StringUtils.getFilenameExtension(fileNameWithExtension); // jpg
        String fileName = fileNameWithExtension.replace("." + extension, ""); // logo_impresionante
        String fileNameToSave = fileName + "_" + System.currentTimeMillis() + "." + extension; // logo_impresionante_423423873845.jpg

        try {
            Path path = Paths.get(imgsLocation).resolve(fileNameToSave);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error al guardar la imagen");
        }
        return fileNameToSave;
    }

    @Override
    public Resource loadFile(String fileName) {

        Path path = Paths.get(imgsLocation).resolve(fileName);
        Resource resource;

        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        if(!resource.exists() || !resource.isReadable())
            throw new RuntimeException();

        return resource;
    }
}
