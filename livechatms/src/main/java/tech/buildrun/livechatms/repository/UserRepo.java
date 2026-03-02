package tech.buildrun.livechatms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.livechatms.model.Users;


public interface UserRepo extends JpaRepository<Users,Long> {

   Optional <Users> findByUsername(String username);

   Users findByEmail(String email);
    
}
