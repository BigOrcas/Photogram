package com.ward.photogram.service;


import com.ward.photogram.config.auth.PrincipalDetails;
import com.ward.photogram.domain.image.Image;
import com.ward.photogram.domain.image.ImageRepository;
import com.ward.photogram.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional(readOnly=true) //영속성 컨텍스트 변경 감지를 해서,더티 체킹,flush(반영) x -> 성능이 괜찮아짐
    public Page<Image> 이미지스토리(int principalId, Pageable pageable){
        Page<Image> images =imageRepository.mStory(principalId,pageable);
        System.out.println("여기돔?");
        // 2(cos) 로그인
        // images에 좋아요 상태 담기
        images.forEach((image)->{

            image.setLikeCount(image.getLikes().size());

            image.getLikes().forEach((like) -> {
                if(like.getUser().getId() == principalId) { // 해당 이미지에 좋아요한 사람들을 찾아서 현재 로긴한 사람이 좋아요 한것인지 비교
                    image.setLikeState(true);
                }
            });

        });
        return images;
    }

    @Value("${file.path}") //properties에 있는 값을 내가 원하는 걸 가져올 수 있음
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
        System.out.println(imageEntity.toString());
    }
}
