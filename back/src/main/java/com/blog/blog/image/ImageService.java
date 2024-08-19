package com.blog.blog.image;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.blog.blog.image.dto.ImageResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private ImageRepository imageRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.bucket}")
    private String bucket;

    public ImageService(ImageRepository imageRepository, AmazonS3Client amazonS3Client) {
        this.imageRepository = imageRepository;
        this.amazonS3Client = amazonS3Client;
    }

    public ImageResponseDto saveImage(MultipartFile file) throws IOException {
        String fileName = "image/"+file.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        amazonS3Client.putObject(bucket,  fileName, file.getInputStream(), objectMetadata);
        String fileUrl = amazonS3Client.getUrl(bucket, fileName).toString();

        Image image = Image.builder()
                .url(fileUrl)
                .build();

        imageRepository.save(image);
        return new ImageResponseDto(image.getId(), image.getUrl());
    }
}
