package com.example.springsocial.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
public class LoginRequest {
	
	@NotBlank(message="El parametro email no puede estar vacio")
    @Email
    @ApiModelProperty(notes="email del usuario")
    private String email;

	
    @NotBlank(message="El parametro password no puede estar vacio")
    @ApiModelProperty(notes="contraseña del usuario")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
