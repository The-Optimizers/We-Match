package com.example.wematch.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Teams {
    @Id
    private Long id;

    public Teams() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
@ManyToOne
private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Column(unique = true)
    private String name;

    private int number;
    private Date time;
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Teams(String name, int number, Date time, String info, String teamType, String ageRange, String imageUrl, String country, String gender, String bio) {
        this.name = name;
        this.number = number;
        this.time = time;
        this.info = info;
        this.teamType = teamType;
        this.ageRange = ageRange;
        this.imageUrl = imageUrl;
        this.country = country;
        this.gender = gender;
        this.bio = bio;
    }

    private String teamType;
    private String ageRange;
    private String imageUrl;
    private String country;
    private String gender;
    private String bio;


    /**
     * name
     * number
     * time
     * info
     * team type
     * age range
     * image
     * country
     * gender
     * bio
     * */
}
