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

    public int register(String email, String username, String password, String confirmPassword) {
        if(userRepo.findByUsername(username).isPresent()) {
            System.out.println("Username already exists: " + username);
            return 1; // Return 1 if username already exists
        }

        if(!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match");
            return 2; // Return 2 if passwords do not match
        }

        if(userRepo.findByEmail(email) != null) {
            System.out.println("Email already exists: " + email);
            return 3; // Return 3 if email already exists
        }

        Users user = new Users(email, username, passwordEncoder.encode(password)); // Hash the password before saving
        userRepo.save(user);
        return 0; // Return 0 if registration is successful
    }

    public boolean verify(Users users) {
        Users user = userRepo.findByUsername(users.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        
        if (!passwordEncoder.matches(users.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return true;
    }
    
}
