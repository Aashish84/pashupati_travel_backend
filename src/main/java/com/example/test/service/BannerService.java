package com.example.test.service;

import com.example.test.entity.Banner;
import com.example.test.helper.FileHelper;
import com.example.test.repo.BannerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BannerService {

    private final FileHelper fileHelper;
    private final BannerRepo repo;

    public Banner add(Banner banner , MultipartFile image) {
        String finalFileName = fileHelper.uploadFile(image);
        if(banner.getId() != 0){
            Optional<Banner> byId = repo.findById(banner.getId());
            if(byId.isPresent()){
                Banner prevBanner = byId.get();
                if(finalFileName != null){
                    fileHelper.deleteFile(prevBanner.getImage());
                    banner.setImage(finalFileName);
                }else{
                    banner.setImage(prevBanner.getImage());
                }
            }
        }else{
            banner.setImage(finalFileName);
        }

        return repo.save(banner);
    }

    public List<Banner> getAll() {
        return repo.findAll();
    }

    public boolean delete(long id) {
        boolean b = false;
        Optional<Banner> byId = repo.findById(id);
        if (byId.isPresent()) {
            b = fileHelper.deleteFile(byId.get().getImage());
            if (b)
                repo.deleteById(id);
        }
        return b;
    }
}
