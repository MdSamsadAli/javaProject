package com.example.demo.service.impl;

import com.example.demo.model.ResourceFile;
import com.example.demo.repository.ResourceFileRepository;
import com.example.demo.service.ResourceFileService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Service
public class ResourceFileServiceImpl
        implements ResourceFileService {

    @Autowired
    private ResourceFileRepository resourceFileRepository;

    @Override
    public ResourceFile addFile(
            MultipartFile multipartFile,
            FileType fileType
    ) {
        String originalFilename =
                multipartFile.getOriginalFilename();
        String filename = getFilename(originalFilename);
        String filePath = Constants.FILE_PATH + filename;
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ResourceFile resourceFile =
                new ResourceFile(originalFilename, filePath, fileType);

        return resourceFileRepository.save(resourceFile);
    }

    private String getFilename(String originalFilename) {
        long currentEpoch = Instant.now().toEpochMilli();
        return String.valueOf(currentEpoch)
                .concat(".")
                        .concat(getExtension(originalFilename));
    }
    private String  getExtension(String filename){
        int i = filename.lastIndexOf('.');
        return filename.substring(i+1);
    }
    @Override
    public ResourceFile findById(int id) {
        return null;
    }


}
