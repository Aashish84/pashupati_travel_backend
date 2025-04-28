package com.example.test.repo;

import com.example.test.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepo extends JpaRepository<Banner, Long> {
}
