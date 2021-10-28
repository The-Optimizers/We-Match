package com.example.wematch.models.DTO;

public class TeamsDTO {




private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String name;
    private int number;
    private String time;
    private String info;
    private String teamType;
    private String ageRange;
    private String imageUrl;
    private String country;
    private String gender;
    private String bio;
    private String cardId;
private  Long isValid;

    public Long getIsValid() {
        return isValid;
    }

    public void setIsValid(Long isValid) {
        this.isValid = isValid;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public TeamsDTO(String user,String name, int number, String time, String info, String teamType, String ageRange, String imageUrl, String country, String gender, String bio) {
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
        this.user=user;
    }

    public TeamsDTO(String user, String name, int number, String time, String info, String teamType, String ageRange, String imageUrl, String country, String gender, String bio, String cardId, Long isValid) {
        this.user = user;
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
        this.cardId = cardId;
        this.isValid = isValid;
    }

    public TeamsDTO() {
        }


    }



