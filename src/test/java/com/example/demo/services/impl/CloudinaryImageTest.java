package com.example.demo.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CloudinaryImageTest {

    private String URL="KIRO";
    private String PUBLIC_ID ="IVAN";

    private CloudinaryImage cloudinaryImage=new CloudinaryImage();

    @Test
    void testUrl(){
        cloudinaryImage.setUrl(URL);
        assertEquals(URL,cloudinaryImage.getUrl());

    }

    @Test
    void testPublicId(){
        cloudinaryImage.setPublicId(PUBLIC_ID);
        assertEquals(PUBLIC_ID,cloudinaryImage.getPublicId());

    }

}