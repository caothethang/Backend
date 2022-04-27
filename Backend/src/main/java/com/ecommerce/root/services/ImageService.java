package com.ecommerce.root.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ImageService {

    Map<String, Object> uploadImage(MultipartFile file) throws IOException;
}
