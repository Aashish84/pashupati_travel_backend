package com.example.test.controller;

import com.example.test.helper.FileHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    private final FileHelper fileHelper;

    @GetMapping("/home")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/file/{name}")
    public ResponseEntity<?> viewImage(@PathVariable("name") String imageName){
        System.out.println(imageName);
        return ResponseEntity
                .ok()
                .contentType(fileHelper.getImageMediaType(imageName))
                .body(fileHelper.getFile(imageName));
    }
}
