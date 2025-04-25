package com.example.test.service;

import com.example.test.entity.FeaturedDestination;
import com.example.test.helper.FileHelper;
import com.example.test.repo.FeaturedDestinationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeaturedDestinationService {

    private final FeaturedDestinationRepo repo;
    private final FileHelper fileHelper;

    public FeaturedDestination add(FeaturedDestination featuredDestination , MultipartFile image) {
        System.out.println("save or update ::: "+featuredDestination);
        String finalFileName = fileHelper.uploadFile(image);
        if(featuredDestination.getId() != 0){
            Optional<FeaturedDestination> byId = repo.findById(featuredDestination.getId());
            if(byId.isPresent()){
                FeaturedDestination prevFeaturedDestination = byId.get();
                if(finalFileName != null){
                    fileHelper.deleteFile(prevFeaturedDestination.getImage());
                    featuredDestination.setImage(finalFileName);
                }else{
                    featuredDestination.setImage(prevFeaturedDestination.getImage());
                }
            }
        }else{
            featuredDestination.setImage(finalFileName);
        }

        return repo.save(featuredDestination);
    }

    public List<FeaturedDestination> getAll() {
        return repo.findAll();
    }

    public boolean delete(long id) {
        boolean b = false;
        Optional<FeaturedDestination> byId = repo.findById(id);
        if (byId.isPresent()) {
            b = fileHelper.deleteFile(byId.get().getImage());
            if (b)
                repo.deleteById(id);
        }
        return b;
    }
}
