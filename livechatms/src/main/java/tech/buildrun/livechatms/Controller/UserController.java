package tech.buildrun.livechatms.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.buildrun.livechatms.model.Users;
import tech.buildrun.livechatms.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // @PostMapping("/register")
    // public ResponseEntity<?> register(@RequestBody Users user){
    //     System.out.println("REGISTER CONTROLLER HIT");
    //     // Users users = new Users(username, password);
    //     boolean success = userService.register(user.getUsername(), user.getPassword());
    //     if(success){
    //         return ResponseEntity.ok("Registration successful!");
    //     } else {
    //         return ResponseEntity.badRequest().body("Registration failed: Username already exists.");
    //     }
    // }
        
    // public Users login(@RequestBody Users user){
    //     System.out.println("LOGIN CONTROLLER HIT");
    //     return userService.verify(user);
    // }
}
