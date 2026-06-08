package com.itms.echallan_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class loginDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
