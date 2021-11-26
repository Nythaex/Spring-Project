package com.example.demo.services.impl;

import com.example.demo.models.entities.Image;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.services.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;
    private CloudinaryServiceImpl cloudinaryService;

    public ImageServiceImpl(ImageRepository imageRepository, CloudinaryServiceImpl cloudinaryService) {
        this.imageRepository = imageRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void delete(Image image) {
        cloudinaryService.delete(image.getPublicId());
        imageRepository.delete(image);
    }
}
