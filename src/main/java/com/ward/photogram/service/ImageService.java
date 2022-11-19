package com.ward.photogram.service;


import com.ward.photogram.config.auth.PrincipalDetails;
import com.ward.photogram.domain.image.Image;
import com.ward.photogram.domain.image.ImageRepository;
import com.ward.photogram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID(); //uuid란 네트워크 상에서 고유성이 보장되는 id를 만들기위한 표준 규약 유일성이 보장된다
        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();// 1.jpg
        System.out.println("이미지 파일이름 :"+imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        // 통신,I/O -> 예외가 발생할수있다.
        try{
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }

        //image 테이블에 저장
        Image image= imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);
        System.out.println(imageEntity);
    }
}
