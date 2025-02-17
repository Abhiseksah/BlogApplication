package io.mountblue.service;

import io.mountblue.dto.LoginDto;
import io.mountblue.models.User;
import io.mountblue.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public String verifyUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return "Invalid password!";
        }
        return "LoggedIn successfully";
    }
}
