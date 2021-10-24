package com.example.wematch.services;


import com.example.wematch.models.Users;
import com.example.wematch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
    @Transactional
    public class UserServices {

        @Autowired
        private UserRepository customerRepo;


        public void updateResetPasswordToken(String token, String email)  {
            Users customer = customerRepo.findByEmail(email);
            if (customer != null) {
                customer.setResetPasswordToken(token);
                customerRepo.save(customer);
            } else {
                System.out.println("Could not find any customer with the email " + email);
            }
        }

        public Users getByResetPasswordToken(String token) {
            return customerRepo.findByResetPasswordToken(token);
        }

        public void updatePassword(Users customer, String newPassword) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(newPassword);
            customer.setPassword(encodedPassword);

            customer.setResetPasswordToken(null);
            customerRepo.save(customer);
        }
    }

