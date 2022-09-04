package dz.tchakal.jwt.service;

import dz.tchakal.jwt.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Charger l'utilisateur par son nom
        UserDto user = userService.findByUsername(username);
        //Charger la liste des privileges
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
