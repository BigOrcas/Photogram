package com.ward.photogram.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@RequiredArgsConstructor
@Controller
public class ImageController {


    @GetMapping({"/", "/image/story"})
    public String story() {

        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular() {

        return "image/popular";
    }
}
