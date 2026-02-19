package tech.buildrun.livechatms.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpSession;
import tech.buildrun.livechatms.Controller.UserController;
import tech.buildrun.livechatms.model.Users;
import tech.buildrun.livechatms.service.UserService;

@Controller
public class PageController{

    @Autowired
    UserService userService;

    Users user;

    @GetMapping("/")
    public String home() {
        //return the login.html that is inside of the templates folder
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // @PostMapping("/login")
    // public String login(@RequestParam String username, @RequestParam String password,HttpSession session, Model model){
    //     try {
    //         if(userService.verify(new Users(username, password))) {
    //             // store logged-in username in session
    //             session.setAttribute("username", username);
    //             return "chat"; // go to chat page
    //         }
    //     } catch (IllegalArgumentException e) {
    //         model.addAttribute("error", e.getMessage());
    //         return "login"; // reload login page with error message
    //     }
    //     model.addAttribute("error", "Login falhou");
    //     return "login";       
    // }
    @GetMapping("/chat")
    public String chat(Model model, Principal principal) {

        model.addAttribute("username", principal.getName());
        return "chat";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model){
        boolean success = userService.register(username, password);
        if(success){
            model.addAttribute("message", "Registration successful! Please log in.");
            return "login";
        } else {
            model.addAttribute("error", "Registration failed: Username already exists.");
            return "register";
        }
    }

}
