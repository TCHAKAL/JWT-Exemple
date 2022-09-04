package dz.tchakal.jwt.model;

import dz.tchakal.jwt.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Table(name = "User")
@DiscriminatorValue("User")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Email
    @Column(name = "email",nullable = false,unique = true)
    private String email;

}
