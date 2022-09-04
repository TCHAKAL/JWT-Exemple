package dz.tchakal.jwt.service;

import dz.tchakal.jwt.dto.UserDto;
import dz.tchakal.jwt.model.User;
import dz.tchakal.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserDto saveUserDto(UserDto userDto){
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return UserDto.fromEntity(userRepository.save(UserDto.toEntity(userDto)));
    }

    public UserDto findByUsername(String username){
        return UserDto.fromEntity(this.userRepository.findByUsername(username));
    }

    public List<UserDto> findAll() {
       return this.userRepository.findAll().stream()
               .map(user -> UserDto.fromEntity(user))
               .collect(Collectors.toList());
    }
}
