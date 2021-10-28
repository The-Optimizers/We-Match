package com.example.wematch.repositories;

import com.example.wematch.models.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface ValidationRepo extends JpaRepository<Validation,Long> {

}
