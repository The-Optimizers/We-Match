package com.example.wematch.models.DTO;

public class FreeLanceDTO {



    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String name;
    private int number;
    private String info;
    private String teamType;
    private String ageRange;
    private String imageUrl;
    private String gender;
    private String bio;
    private  String skills;
    private String cardId;

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

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public FreeLanceDTO(String user, String name, int number, String info, String teamType, String ageRange, String imageUrl, String gender, String bio, String skills) {
        this.name = name;
        this.number = number;
        this.info = info;
        this.teamType = teamType;
        this.ageRange = ageRange;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.bio = bio;
        this.user=user;
        this.skills=skills;
    }

    public FreeLanceDTO() {
    }

}
