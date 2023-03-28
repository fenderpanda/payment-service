package com.example.paymentservice.controller;

import com.example.paymentservice.service.UserBalanceService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserBalanceController {
    private final UserBalanceService userBalanceService;

    public UserBalanceController(UserBalanceService userBalanceService) {
        this.userBalanceService = userBalanceService;
    }

    @GetMapping("/balance")
    public int getUserBalance(JwtAuthenticationToken token) {

        String userId = token.getToken().getSubject();

        return userBalanceService.getUserBalance(userId);
    }
}
