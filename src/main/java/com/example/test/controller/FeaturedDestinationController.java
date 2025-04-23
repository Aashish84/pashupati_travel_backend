package com.example.test.controller;

import com.example.test.entity.FeaturedDestination;
import com.example.test.helper.FileHelper;
import com.example.test.service.FeaturedDestinationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/admin/featuredDestination")
@AllArgsConstructor
public class FeaturedDestinationController {

    private final FeaturedDestinationService featuredDestinationService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FeaturedDestination> addFeaturedDestination(@RequestPart String featuredDestinationString, @RequestPart("image") MultipartFile image) throws JsonProcessingException {
        FeaturedDestination featuredDestination = new ObjectMapper().readValue(featuredDestinationString, FeaturedDestination.class);
        System.out.println(featuredDestination);
        System.out.println(image.getContentType());
        FeaturedDestination result = featuredDestinationService.add(featuredDestination , image);
        return ResponseEntity.ok(result);
    }

}
