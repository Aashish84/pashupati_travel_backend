package com.example.test.service;

import com.example.test.entity.FeaturedDestination;
import com.example.test.helper.FileHelper;
import com.example.test.repo.FeaturedDestinationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class FeaturedDestinationService {

    private final FeaturedDestinationRepo repo;
    private final FileHelper fileHelper;

    public FeaturedDestination add(FeaturedDestination featuredDestination , MultipartFile image) {
        String finalFileName = fileHelper.uploadFile(image);
        featuredDestination.setImage(finalFileName);
        return repo.save(featuredDestination);
    }

    public List<FeaturedDestination> getAll() {
        return repo.findAll();
    }
}
