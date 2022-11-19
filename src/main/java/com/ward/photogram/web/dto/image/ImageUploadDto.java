package com.ward.photogram.web.dto.image;

import com.ward.photogram.domain.image.Image;
import com.ward.photogram.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadDto {
    private MultipartFile file;
    private String caption;

    public Image toEntity(String postImageUrl, User user){
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user) // 어떤유저가 사진을 insert햇는지
                .build();
    }
}
