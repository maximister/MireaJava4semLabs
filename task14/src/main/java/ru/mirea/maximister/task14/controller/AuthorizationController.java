package ru.mirea.maximister.task14.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.maximister.task14.model.dto.SecurityUserDto;
import ru.mirea.maximister.task14.service.security.AuthorizationService;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    AuthorizationService authorizationService;

    @PostMapping("/signup")
    public String signUp(@RequestBody SecurityUserDto user){
        if(authorizationService.signUp(user)){
            return "User created";
        }else{
            return "User exists";
        }
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "Logged out successfully";
    }

}