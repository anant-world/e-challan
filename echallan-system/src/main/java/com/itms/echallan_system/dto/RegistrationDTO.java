package com.itms.echallan_system.dto;

import com.itms.echallan_system.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegistrationDTO {

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "Email is required")

    private String email;

    @NotBlank(message = "password is reuired")
    private String password;

    private Role role;

}
