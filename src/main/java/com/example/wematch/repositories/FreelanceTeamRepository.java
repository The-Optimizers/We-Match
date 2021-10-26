package com.example.wematch.repositories;

import com.example.wematch.models.FreeLanceTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelanceTeamRepository extends JpaRepository<FreeLanceTeam,Long> {

        public FreeLanceTeam findAllById(Long id);



    }


