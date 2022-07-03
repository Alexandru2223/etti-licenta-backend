package com.akhianand.springrolejwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    @Email(message = "Email incorect")
    private String username;
    @Length(min = 8, message = "Parola trebuie sa aiba minim 8 caractere")
    private String password;
    @Length(min = 10, max = 10, message = "Numarul de telefon trebuie sa aiba 10 cifre")
    private String phone;
    @NotEmpty(message = "Numele nu trebuie sa fie gol")
    private String name;
    private String businessTitle;
    private String birthdate;
    private String avatar;

}
