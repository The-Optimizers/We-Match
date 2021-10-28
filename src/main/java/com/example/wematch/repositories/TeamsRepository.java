package com.example.wematch.repositories;

import com.example.wematch.models.Role;
import com.example.wematch.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableJpaRepositories
public interface TeamsRepository extends JpaRepository<Teams,Long> {

    public Teams findAllById(Long id);
    Teams findByName(String name);
//    List<Teams> findAllByTeam_Name(String name);





}
