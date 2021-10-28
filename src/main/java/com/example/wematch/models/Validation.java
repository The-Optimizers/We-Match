package com.example.wematch.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Validation {
    private Long id;

    public Validation() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }


    @Column
    private Long user_id;

    private Long [] uid;

@Column
private Long team_id;


    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
