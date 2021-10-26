package com.example.wematch.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class PostsDTO {
    private String body;
    private String user;
     private Date timeStamp;

//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @LastModifiedDate
//    private Date updatedAt;

    public PostsDTO(String body, String user, Date timeStamp) {
        this.body = body;
        this.user = user;
        this.timeStamp=timeStamp;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public Date setTimeStamp(Date timeStamp) {
        return this.timeStamp;
    }

    public PostsDTO(){}

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
