package com.itms.echallan_system.service;

import com.itms.echallan_system.dto.RegistrationDTO;
import com.itms.echallan_system.entity.User;
import com.itms.echallan_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(RegistrationDTO dto){

        if(userRepository.existsByUsername(dto.getUsername())){
            throw new RuntimeException("username already exsist");
        }
        User user= new User();

        user.setUsername(dto.getUsername());

        user.setEmail(dto.getEmail());

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setRole(dto.getRole());

        userRepository.save(user);

        return "user registered successfully";


    }

}
