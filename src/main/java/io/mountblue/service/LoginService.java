package io.mountblue.service;

import io.mountblue.dto.LoginDto;

public interface LoginService {
    String verifyUser(LoginDto loginDto);
}
