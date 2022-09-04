package dz.tchakal.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;
}
