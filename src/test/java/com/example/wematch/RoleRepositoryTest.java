package com.example.wematch;

import com.example.wematch.models.Role;
import com.example.wematch.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateRoles() {
        Role user = new Role("User");
        Role admin = new Role("Admin");
        Role customer = new Role("Customer");
//        List<List<Integer>> list = Arrays.asList(Arrays.asList(1,2), Arrays.asList(3,4));
//        List<List<Integer>> list = List.of(List.of(1,2), List.of(3,4));
        //repo.saveAll(List.of(user, admin, customer));
      repo.saveAll(Arrays.asList(user,admin,customer));

        List<Role> listRoles = repo.findAll();

        assert(listRoles.size())==(3);
    }

}
