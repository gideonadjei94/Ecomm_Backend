package com.EcommBackend.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @RequestMapping("/")
    public String greet(){
        return "Hello from SpringBoot";
    }

}
