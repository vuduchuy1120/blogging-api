package com.example.bloggingaplication.services.implement;

import com.example.bloggingaplication.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImp implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //Filename
        String fileName = file.getOriginalFilename();
        //abc.png

        //random name generator file
        String randomID = java.util.UUID.randomUUID().toString();
        String fileName1 = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

        // full path
        String filePath = path + File.separator + fileName1;

        // create folder if not create
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        // filee coppy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path + File.separator + name;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
