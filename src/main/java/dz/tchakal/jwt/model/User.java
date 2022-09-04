package dz.tchakal.jwt.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Table(name = "User")
@DiscriminatorValue("User")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

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
