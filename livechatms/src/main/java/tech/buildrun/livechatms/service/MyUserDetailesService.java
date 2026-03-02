package tech.buildrun.livechatms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tech.buildrun.livechatms.model.UserPrincipal;
import tech.buildrun.livechatms.model.Users;
import tech.buildrun.livechatms.repository.UserRepo;

@Service
public class MyUserDetailesService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Here!");
        Users user = userRepo.findByEmail(email);
        if(user == null) {
            System.out.println("User not found with email: " + email);
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        System.out.println("passou");
        return new UserPrincipal(user);
    }
    
}
