package com.example.test.controller;

import com.example.test.entity.Banner;
import com.example.test.entity.FeaturedDestination;
import com.example.test.service.BannerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/banner")
@AllArgsConstructor
public class BannerController {
    private final ObjectMapper objectMapper;
    private final BannerService bannerService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Banner> addBanner(@RequestPart String bannerString, @RequestPart(value = "image" , required = false) MultipartFile image) throws JsonProcessingException {
        Banner banner = objectMapper.readValue(bannerString, Banner.class);
        Banner result = bannerService.add(banner , image);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Banner>> getBanners() {
        List<Banner> allData = bannerService.getAll();
        return ResponseEntity.ok(allData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBannerHardDelete(@PathVariable long id) {
        boolean delete = bannerService.delete(id);
        return ResponseEntity.ok(delete);
    }
}
