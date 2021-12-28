package com.garden.root.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ImageService {
    
    CompletableFuture<String> uploadImage(MultipartFile file) throws IOException;
}
