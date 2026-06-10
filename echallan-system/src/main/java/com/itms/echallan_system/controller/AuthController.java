package com.itms.echallan_system.controller;


import com.itms.echallan_system.dto.RegistrationDTO;
import com.itms.echallan_system.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterService registerService;

    public AuthController(RegisterService registerService){
        this.registerService=registerService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationDTO request){
       registerService.registerUser(request);
       return  ResponseEntity.status(HttpStatus.CREATED).body("user registered successfully");

    }
}
