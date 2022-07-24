package com.example.demo.controller;

import com.example.demo.model.ResourceFile;
import com.example.demo.service.ResourceFileService;
import com.example.demo.utils.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private ResourceFileService resourceFileService;

    @PostMapping
    public ResourceFile uploadFile
            (@RequestParam MultipartFile file, @RequestParam FileType fileType){
        return resourceFileService.addFile(file, fileType);
    }
}
