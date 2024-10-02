package com.api.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter

public class RegistrationDto {
    @NotEmpty
    @Size(min=2, message = "name should be min 2 letters")
    private String name;
    @Email(message = "email format is not correct")
    private String email;
    @NotEmpty
    @Size(min = 10, max = 10, message = "mobile number should be min 10 digits")
    private String mobile;

}