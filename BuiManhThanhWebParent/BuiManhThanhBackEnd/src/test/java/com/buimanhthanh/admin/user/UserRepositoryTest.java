package com.buimanhthanh.admin.user;

import com.buimanhthanh.common.entity.Role;
import com.buimanhthanh.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUserWithOneRole(){
        User thanh = new User(null,"manhthanh147@gmail.com","Thanh25102","bui","thanh",null,true,null);
        Role roleAdmin = entityManager.find(Role.class,2);
        assertNotNull(roleAdmin);

        thanh.addRole(roleAdmin);

        User savedThanh = repo.save(thanh);

        assertNotNull(savedThanh);
    }

    @Test
    public void testCreateUserWithTwoRole(){
        User thanh = new User(null,"manhthanh159753@gmail.com","Thanh25102","bui","thanh",null,true,null);
        Role roleEditor = entityManager.find(Role.class,4);
        Role roleAssistant = entityManager.find(Role.class,6);
        thanh.addRole(roleEditor);
        thanh.addRole(roleAssistant);

        User savedThanh = repo.save(thanh);
        assertNotNull(savedThanh);
    }

    @Test
    public void testListAllUser(){
        List<User> users =(List<User>) repo.findAll();
        users.forEach(System.out::println);
    }

    @Test
    public void testGetUserById(){
        User user = repo.findById(2).get();
        assertNotNull(user);
    }

    @Test
    public void testUpdateUserDetails(){
        User user = repo.findById(2).get();
        user.setEnabled(false);
        user.setEmail("Thanh25102@gmail.com");
        repo.save(user);
    }

    @Test
    public void testRoleOfUser(){
        User user = repo.findById(2).get();
        Role role = entityManager.find(Role.class,4);
        Role roleSalesPerson = entityManager.find(Role.class,5);

        user.getRoles().remove(role);
        user.addRole(roleSalesPerson);

        repo.save(user);
    }

    @Test
    public void testDeleteUser(){
        Integer userId = 2;
        repo.deleteById(userId);
        assertNull(repo.findById(userId));
    }

    @Test
    public void testGetUserByEmail(){
        String emailNotNull = "manhthanh147@gmail.com";
        String emailNull = "2manhthanh159753@gmail.com";

        User user = repo.getUserByEmail(emailNotNull);
        User userNull = repo.getUserByEmail(emailNull);

        assertNotNull(user);
        assertNull(userNull);
    }

    @Test
    public void testCountById(){
        Integer id = 100;
        Long countById = repo.countById(id);
        assertEquals(0,countById);
    }

    @Test
    public void testUpdateUserStatus(){
        User user = repo.findById(21).get();
        repo.changeStatus(user.getId(), !user.isEnabled());

        User user2 = repo.findById(21).get();
        assertEquals(user2.isEnabled(),!user.isEnabled());
    }

    @Test
    public void testGetPage1(){
        int pageNumber = 0;
        int pageSize = 8;
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<User> page = repo.findAll(pageable);
        List<User> content = page.getContent();
        content.forEach(System.out::println);

        assertEquals(pageSize,content.size());
    }
}