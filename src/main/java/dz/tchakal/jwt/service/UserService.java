package dz.tchakal.jwt.service;

import dz.tchakal.jwt.dto.UserDto;
import dz.tchakal.jwt.model.User;
import dz.tchakal.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User saveUserDto(UserDto userDto){
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return userRepository.save(UserDto.toEntity(userDto));
    }

    public User findByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
}
