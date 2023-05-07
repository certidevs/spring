package com.example.controllers;

import com.example.services.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class FileController {

    private final FileService fileService;

    @GetMapping("files/{filename:.+}")
    public ResponseEntity<Resource> showImage(@PathVariable String filename) {
        Resource file = fileService.loadFile(filename);
        return ResponseEntity.ok().body(file);
    }
}
