package com.alexra.my_notebook.controller;
import com.alexra.my_notebook.entity.User;
import com.alexra.my_notebook.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;
import java.util.List;

@Controller
public class MyController {

    public  static final Logger LOGGER = Logger.getLogger(MyController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showAllUsers(Model model){
        List<User>  allUsers = userService.getAllUsers();
        LOGGER.info("number of users : "+ allUsers.size());
        LOGGER.debug("print allUsers "+ Arrays.toString(allUsers.toArray()));
        model.addAttribute("allUsers", allUsers);
        LOGGER.error("add all users");
        return "all-users";
    }

    @RequestMapping("/addNewUser")
    public String addNewUser(Model model){
        User user = new User();
        model.addAttribute("user",user);
        LOGGER.error("add empty user");
        return "user-info";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user){
        LOGGER.info( "user = "+ user.toString());
        userService.saveUser(user);
        LOGGER.error("save user");
        return "redirect:/";
    }

    @RequestMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") int id, Model model){
        User user =  userService.getUser(id);
        LOGGER.info( "id user =" + id);
        LOGGER.info("user = "+ user.toString());
        model.addAttribute("user", user);
        LOGGER.error("update user ="+ user.toString());
        return "user-info";
    }

    @RequestMapping("/deleteUser")
    public  String deleteUser(@RequestParam("userId") int id){
        userService.deleteUser(id);
        LOGGER.error("delete user ="+id);
        return "redirect:/";
    }
}
