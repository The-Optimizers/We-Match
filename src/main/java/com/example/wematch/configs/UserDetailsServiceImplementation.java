package com.example.wematch.configs;

import com.example.wematch.models.Users;
import com.example.wematch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    UserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//            return applicationUserRepository.findByUsername(username);
        Users users = applicationUserRepository.findByUsername(username);

        if (users == null) {
            System.out.print("Username not found");
            throw new UsernameNotFoundException((username + " not found"));
        }
        return users;
        }

}

