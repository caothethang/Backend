package com.garden.root.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.garden.root.services.ImageService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ImageServiceImpl implements ImageService {
    
    private final Cloudinary cloudinary;
    
    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    
    @Override
    @Async
    public CompletableFuture<String> uploadImage(MultipartFile file) throws IOException {
        return CompletableFuture.supplyAsync(()->{
            Map<String, Object> cloudinaryUrl = null;
    
            Map<String,Object> uploadResult = null;
            try {
                uploadResult = cloudinary.uploader().uploadLarge(file.getBytes(), ObjectUtils.asMap("resource_type","auto"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            cloudinaryUrl = uploadResult;
            String url = (String) cloudinaryUrl.get("url");
            return url;
        });
        
    }
}
