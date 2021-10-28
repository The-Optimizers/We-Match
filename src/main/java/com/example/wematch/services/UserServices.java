package com.example.wematch.services;


import com.example.wematch.models.Role;
import com.example.wematch.models.Teams;
import com.example.wematch.models.Users;
import com.example.wematch.repositories.RoleRepository;
import com.example.wematch.repositories.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

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
    @Autowired
     UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    Role user = new Role("User");
    Role admin = new Role("Admin");
    Role customer = new Role("Customer");
    public void rolesMethod(){
        roleRepo.saveAll(Arrays.asList(user, admin, customer));

    }

    public void registerDefaultUser(Users user) {
        Role roleUser = roleRepo.findByName("User");
        user.addRole(roleUser);
        userRepo.save(user);
    }

//    @Resource(name ="sessionFactory")
//    private SessionFactory sessionFactory;
//
//    public void mergeWithExistingAndUpdate(final Teams teams) {
//
//        Session session = sessionFactory.getCurrentSession();
//
//        Teams existingPerson = (Teams) session.get(Teams.class, teams.getId());
//        /** * name * number * time * info * team type * age range * image * country * gender * bio * */
//        // set here explicitly what must/can be overwritten by the html form POST
//        existingPerson.setName(teams.getName());
//        existingPerson.setNumber(teams.getNumber());
//       existingPerson.setTime(teams.getTime());
//
//        session.update(existingPerson);
//    }

    public List<Users> listAll() {
        return userRepo.findAll();
    }

    public Users get(Long id) {
        return userRepo.findById(id).get();
    }

    public List<Role> listRoles() {
        return roleRepo.findAll();
    }



    }

