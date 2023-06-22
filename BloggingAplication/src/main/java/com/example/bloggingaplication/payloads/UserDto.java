package com.example.bloggingaplication.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
    private Integer id;
    @NotEmpty
    @Size(min = 4, message = "username is not empty@")
    private String name;
    @Email(message = "Email is not valid!")
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 3, max = 25, message = "Password must be min of 3 character and max of 25 character!")
    private String password;
    @NotEmpty

    private String about;
}
