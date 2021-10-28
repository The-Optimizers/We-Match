package com.example.wematch.repositories;

import com.example.wematch.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Long> {
}
