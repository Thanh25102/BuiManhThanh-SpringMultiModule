package com.buimanhthanh.admin.user;

import com.buimanhthanh.common.entity.Role;
import com.buimanhthanh.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {

    @Autowired private UserRepository userRepo;
    @Autowired private RoleRepository roleRepo;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return (List<User>) userRepo.findAll();
    }

    public List<Role> findAllRole(){
        return (List<Role>) roleRepo.findAll();
    }

    public void saveUser(User user){
        if(user.getId() != null){
            User userExist = userRepo.findById(user.getId()).get();
            if(user.getPassword().trim().isEmpty()){
                user.setPassword(userExist.getPassword());
            }else{
                encodePassword(user);
            }
        }else{
            encodePassword(user);
        }


        userRepo.save(user);
    }

    public void encodePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public boolean isEmailUnique(String email,Integer id){
        User user = userRepo.getUserByEmail(email);
        if(user == null) return true;

        if(id != -1){
            if(user.getId() != id)
                return false;
        }
        else
            if(user != null)
                return false;
        return true;
    }

    public User get(Integer id) throws UserNotFoundException {
        try{
            return userRepo.findById(id).get();
        }catch (NoSuchElementException e){
            throw new UserNotFoundException("Could not found any user with ID : " + id);
        }
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long countById = userRepo.countById(id);
        if(countById == 0){
            throw new UserNotFoundException("Could not find user with id "+ id);
        }
        userRepo.deleteById(id);
    }

    public void changeStatus(Integer id, Boolean enabled) throws UserNotFoundException {
        Long countById = userRepo.countById(id);
        if(countById == 0){
            throw new UserNotFoundException("Could not find user with id "+ id);
        }
        userRepo.changeStatus(id,enabled);
    }
}
