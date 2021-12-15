package com.garden.root.controller;

import com.garden.root.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/images")
public class ImageController {
    
    private final ImageService imageService;
    
    
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    
    @PostMapping("/upload")
    public ResponseEntity uploadImage(@RequestParam("files")MultipartFile file){
        try {
            String url = (String) imageService.uploadImage(file).get("url");
            return ResponseEntity.status(HttpStatus.OK).body(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
    }
}
