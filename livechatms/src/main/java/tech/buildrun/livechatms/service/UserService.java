package tech.buildrun.livechatms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tech.buildrun.livechatms.model.Users;
import tech.buildrun.livechatms.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public boolean register(String username, String password) {
        if(userRepo.findByUsername(username).isPresent()) {
            System.out.println("Username already exists: " + username);
            return false;
        }
        Users user = new Users(username, passwordEncoder.encode(password)); // Hash the password before saving
        userRepo.save(user);
        return true;
    }

    // public boolean verify(Users users) {
    //     Users user = userRepo.findByUsername(users.getUsername())
    //         .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        
    //     if (!passwordEncoder.matches(users.getPassword(), user.getPassword())) {
    //         throw new IllegalArgumentException("Invalid credentials");
    //     }

    //     return true;
    // }
    
}
