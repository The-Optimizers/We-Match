package com.example.wematch.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class Users  implements UserDetails {
    private static final Long serialVersionUID = -909242657352530550L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @Column(unique = true)
     String username;

    @Column(unique = true)
    private String email;


    @OneToMany(mappedBy = "users")
    List<Posts>posts;
    public List<Posts> getPosts() {
        return posts;
    }
//
//    public List<Teams> getTeams() {
//        return teams;
//    }
//
//    public void setTeams(List<Teams> teams) {
//        this.teams = teams;
//    }

    @OneToMany(mappedBy = "users")
    public  List<Teams> teams;


    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }


    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @OneToMany(mappedBy = "users")
    public List<Teams> createdTeam;

//    public List<Teams> getTeams() {
//        return teams;
//    }
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "joind_teams",
//            joinColumns = @JoinColumn(name = "team_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    Set<Users> users = new HashSet<>();

//    @ManyToMany(mappedBy = "users")
//    Set<Users> team = new HashSet<>();
/******************************* joined teams ************************************/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "joind_teams",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Teams> team = new HashSet<>();




    public Set<Teams> getTeam() {
        return team;
    }

    public void setTeam(Set<Teams> team) {
        this.team = team;
    }

    /*************************** end joined teams *************************************/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();



//    public Set<Users> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<Users> users) {

    @Override
    public String toString() {
        return  username ;
    }
//        this.users = users;
//    }
//
//    public Set<Users> getTeam() {
//        return team;
//    }
//
//    public void setTeam(Set<Users> team) {
//        this.team = team;
//    }

//    public void setTeams(List<Teams> teams) {
//        this.teams = teams;
//    }

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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        return authorities;
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


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }


}
