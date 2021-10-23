package com.example.wematch.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Users  implements UserDetails {
    @Id
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "users")
    List<Posts>posts;

    public List<Posts> getPosts() {
        return posts;
    }

    @OneToMany(mappedBy = "users")
    private List<Teams> teams;

    public List<Teams> getTeams() {
        return teams;
    }
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "joind_teams",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    Set<Users> users = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    Set<Users> team = new HashSet<>();



    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    public Set<Users> getTeam() {
        return team;
    }

    public void setTeam(Set<Users> team) {
        this.team = team;
    }

    public void setTeams(List<Teams> teams) {
        this.teams = teams;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    public Users(String username, String email, String password, String imageUrl) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
    }

    private String password;
    private String imageUrl;

    public Users() {

    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
