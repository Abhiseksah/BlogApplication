package io.mountblue.service;
import io.mountblue.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.mountblue.dto.UserDto;
import io.mountblue.repository.UserRepository;

@Service
public class UserServiceImpl implements  UserService{
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public String addUser(UserDto userDto) {
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
            return "Confirm password not matching";
        }
        if (userRepository.findByEmail(userDto.getEmail())!=null) {
            return "User already exists with this email!";
        }
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(hashedPassword);
        User user = new User(userDto.getName(),userDto.getEmail(),userDto.getPassword(),userDto.getRole());
        userRepository.save(user);
        return "User registered successfully";
    }
}
