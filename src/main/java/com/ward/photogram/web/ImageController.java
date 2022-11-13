package com.ward.photogram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {

    @GetMapping({"/","/image/story"})
    public String story(){
        return "image/story";
    }

    @GetMapping({"/","/image/popular"})
    public String popular(){
        return "image/popular";
    }
}
