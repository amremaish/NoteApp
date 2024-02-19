package com.notes.util;

import com.notes.error.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Component
public class ImageUploader {

    private static final String RESOURCES_DIR = "src/main/resources/uploads";

    private static final List<String> ALLOWED_IMAGE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");

    public static String uploadImage(MultipartFile image, long userId, String folderName) {
        if (image == null || image.isEmpty()) {
            throw new CustomException("Please select an image to upload.");
        }
        String fileLink;
        // Get the filename without extension
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        try {
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            // Check if the file extension is in the allowed image extensions list
            if (!ALLOWED_IMAGE_EXTENSIONS.contains(fileExtension)) {
                throw new CustomException("Invalid file type. Only JPG, JPEG, PNG, and GIF images are allowed.");
            }

            // Save the file to the uploads directory
            String uploadDir = RESOURCES_DIR + "/" + userId + "/" + folderName;
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(image.getInputStream(), filePath);


            System.out.println("File uploaded successfully. Link: " + fileName);
        } catch (FileAlreadyExistsException ex) {
            throw new CustomException("Image already exists in the " + folderName + " folder.");
        } catch (IOException ex) {
            throw new CustomException(ex.getMessage());
        }
        return fileName;
    }

    public static void removeImage( long userId, String folderName, String fileName) {
        try {
            String uploadDir = RESOURCES_DIR + "/" + userId + "/" + folderName ;
            Path imagePath = Paths.get(uploadDir).resolve(fileName);

            // Check if the file exists
            if (Files.exists(imagePath)) {
                // Delete the file
                Files.delete(imagePath);
            } else {
                throw new CustomException("Image not found in the " + folderName + " folder.");
            }
        } catch (IOException ex) {
            throw new CustomException("Failed to remove image: " + ex.getMessage());
        }
    }
}
