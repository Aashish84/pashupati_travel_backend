package com.example.test.controller;

import com.example.test.entity.Banner;
import com.example.test.entity.FeaturedDestination;
import com.example.test.helper.FileHelper;
import com.example.test.service.BannerService;
import com.example.test.service.FeaturedDestinationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
//@CrossOrigin
public class GenericController {

    private final FileHelper fileHelper;
    private final FeaturedDestinationService featuredDestinationService;
    private final BannerService bannerService;

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

    @GetMapping("/featuredDestination")
    public ResponseEntity<List<FeaturedDestination>> getFeaturedDestinations() {
        List<FeaturedDestination> allData = featuredDestinationService.getAll();
        return ResponseEntity.ok(allData);
    }

    @GetMapping("/banner")
    public ResponseEntity<List<Banner>> getBanners() {
        List<Banner> allData = bannerService.getAll();
        return ResponseEntity.ok(allData);
    }
}
