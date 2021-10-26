package com.example.wematch.models;

import javax.persistence.*;

@Entity
public class FreeLanceTeam {


        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Column(updatable = false)
        private String cardId;

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public FreeLanceTeam() {

        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }


        public Users getUsers() {
            return users;
        }

        public void setUsers(Users users) {
            this.users = users;
        }

        @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
        @JoinColumn(name = "app_user_id", nullable = false,updatable = false)
        public Users users;

        public FreeLanceTeam(Users users, String name, int number, String info, String teamType, String ageRange, String imageUrl, String gender, String bio, String skills) {
            this.users = users;
            this.name = name;
            this.number = number;
            this.info = info;
            this.teamType = teamType;
            this.ageRange = ageRange;
            this.imageUrl = imageUrl;
            this.gender = gender;
            this.bio = bio;
            this.skills=skills;
        }

        @Column(unique = true)
        private String name;

        private int number;
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

//    public Teams(String name, int number, String time, String info, String teamType, String ageRange, String imageUrl, String country, String gender, String bio) {
//        this.name = name;
//        this.number = number;
//        this.time = time;
//        this.info = info;
//        this.teamType = teamType;
//        this.ageRange = ageRange;
//        this.imageUrl = imageUrl;
//        this.country = country;
//        this.gender = gender;
//        this.bio = bio;
//    }

        private String teamType;
        private String ageRange;
        private String imageUrl;
        private String gender;
        private String bio;
        private String skills;


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


