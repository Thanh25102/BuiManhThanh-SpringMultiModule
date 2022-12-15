package com.buimanhthanh.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    @Autowired private UserService service;

    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(@RequestParam("email") String email,@RequestParam(value = "id",defaultValue = "-1") Integer id){
        return service.isEmailUnique(email,id) ? "OK" : "Duplicated";
    }

}
