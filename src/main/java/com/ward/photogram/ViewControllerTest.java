package com.ward.photogram;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class ViewControllerTest {

    @GetMapping("/auth/signup")
    public String signupPage() {
        return "auth/signup";
    }

    @RequestMapping(value = "/auth/signin", method= {RequestMethod.GET, RequestMethod.POST})
    public String signinPage() {
        System.out.println(signinPage().toString());
        return "auth/signin";
    }

    @GetMapping("/image/story")
    public String storyPage() {
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popularPage() {
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String uploadPage() {
        return "image/upload";
    }

    @GetMapping("/user/profile")
    public String profilePage() {
        return "user/profile";
    }

    @GetMapping("/user/update")
    public String updatePage() {
        return "user/update";
    }
}
