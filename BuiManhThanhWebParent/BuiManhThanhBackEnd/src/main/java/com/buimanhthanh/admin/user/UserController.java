package com.buimanhthanh.admin.user;

import com.buimanhthanh.common.entity.Role;
import com.buimanhthanh.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String userHome(ModelMap modelMap){
        List<User> users = service.findAll();
        modelMap.addAttribute("users",users);
        return "users";
    }

    @GetMapping("/users/new")
    public String newUser(ModelMap modelMap){
        User user = new User();
        user.setEnabled(true);
        List<Role> roles = service.findAllRole();
        modelMap.addAttribute("user",user);
        modelMap.addAttribute("roles",roles);
        modelMap.addAttribute("pageTitle","Create new user");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user, RedirectAttributes redirect){
        service.saveUser(user);
        redirect.addFlashAttribute("message","User has been created");
        return "redirect:/users";
    }

    @GetMapping("users/edit/{id}")
    public String updateUser(@PathVariable Integer id,RedirectAttributes redirect,ModelMap modelMap){
        try{
            User user  = service.get(id);
            modelMap.addAttribute("user",user);
            modelMap.addAttribute("pageTitle","Edit :"+user.getEmail());
            List<Role> roles = service.findAllRole();
            modelMap.addAttribute("roles",roles);
            return "user_form";
        }catch (UserNotFoundException e){
            redirect.addFlashAttribute("message",e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable Integer id,RedirectAttributes redirect,ModelMap modelMap){
        try{
            service.delete(id);
            redirect.addFlashAttribute("message","Deleted successfully");
        }catch (UserNotFoundException e){
            redirect.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("users/{id}/enabled/{enabled}")
    public String changeStatusUser(RedirectAttributes redirect,@PathVariable("id") Integer id, @PathVariable("enabled") Boolean enabled){
       try{
           service.changeStatus(id,enabled);
           String status = enabled ? "Enabled" : "Disabled";
           redirect.addFlashAttribute("message",status + " user : "+id +" successfully");
       } catch (UserNotFoundException e){
           redirect.addFlashAttribute("message",e.getMessage());
       }
        return "redirect:/users";
    }

}
