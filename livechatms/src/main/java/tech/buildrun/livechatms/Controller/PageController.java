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

    @GetMapping("/chat")
    public String chat(Model model, Principal principal) { // Principal é injetado automaticamente pelo Spring Security
        model.addAttribute("username", principal.getName());
        return "chat";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String confirmPassword, Model model){

        int success = 0;

        success = userService.register(username, password,confirmPassword);
        //switch case para verificar o valor de success e retornar a mensagem de erro correspondente
        switch (success) {
            case 0:
                System.out.println("Registration successful for user: " + username);
                model.addAttribute("message", "Registration successful! Please log in.");
                return "redirect:/login";
            case 1:
                System.out.println("Username already exists: " + username);
                model.addAttribute("error", "Username already exists");
                return "register";
            case 2:
                System.out.println("Passwords do not match");
                model.addAttribute("error", "Passwords do not match");
                return "register";
            default:
                System.out.println("Unknown error during registration");
                model.addAttribute("error", "Unknown error during registration");
                return "register";
        }
    }

}
