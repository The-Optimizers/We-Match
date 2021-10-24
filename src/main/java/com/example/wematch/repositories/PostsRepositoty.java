package com.example.wematch.repositories;

import com.example.wematch.models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepositoty extends JpaRepository<Posts, Long> {
}