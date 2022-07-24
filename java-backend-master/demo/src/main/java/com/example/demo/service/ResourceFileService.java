package com.example.demo.service;

import com.example.demo.model.ResourceFile;
import com.example.demo.utils.FileType;
import org.springframework.web.multipart.MultipartFile;

public interface ResourceFileService {
    ResourceFile addFile(MultipartFile multipartFile,
                         FileType fileType);

    ResourceFile findById(int id);
}
