package com.garden.root.controller;

import com.garden.root.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
@RequestMapping("/api/images")
public class ImageController {
    
    private final ImageService imageService;
    
    
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    
    @PostMapping("/upload")
    public ResponseEntity uploadImage(@RequestParam("files")MultipartFile file){
        System.out.println(file.getOriginalFilename());
        try {
            CompletableFuture<String> url = imageService.uploadImage(file);
            String data = url.get();
            System.out.println(data);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Error");
    }
}
