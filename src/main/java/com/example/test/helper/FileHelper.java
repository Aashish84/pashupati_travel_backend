package com.example.test.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileHelper {
    @Value("${file.upload.path}")
    private String FILE_PATH;

    public MediaType getImageMediaType(String imageName) {
        String extension = StringUtils.getFilenameExtension(imageName);
        if (extension != null) {
            return switch (extension.toLowerCase()) {
                case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
                case "png" -> MediaType.IMAGE_PNG;
                case "gif" -> MediaType.IMAGE_GIF;
                case "mp4", "mpeg" -> MediaType.valueOf("video/mp4");
                case "webm" -> MediaType.valueOf("video/webm");
                default -> MediaType.APPLICATION_OCTET_STREAM;
            };
        }
        return MediaType.APPLICATION_OCTET_STREAM;
    }

    public String uploadFile(MultipartFile file) {
        try{
            if (file != null && !file.isEmpty()) {
                long currentTimeMillis = System.currentTimeMillis();
                String fileName = currentTimeMillis+"_"+file.getOriginalFilename();
                String filePath = FILE_PATH + File.separator + fileName;
                System.out.println(filePath);
                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                File copiedFile = new File(filePath);
                boolean exists = copiedFile.exists();
                if (exists) {
                    return fileName;
                }
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public InputStreamResource getFile(String fileName){
        File file = new File(FILE_PATH + File.separator + fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            return new InputStreamResource(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteFile(String fileName) {
        try {
            if (fileName != null && !fileName.isEmpty()) {
                String filePath = FILE_PATH + File.separator + fileName;
                File fileToDelete = new File(filePath);
                if (fileToDelete.exists()) {
                    Files.deleteIfExists(Paths.get(filePath));
                    return true;
                }
                return false; // File doesn't exist
            }
            return false; // Invalid file name
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
