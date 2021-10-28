package com.example.wematch.repositories;

import com.example.wematch.models.Posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByUsers_Username(String name);
}
