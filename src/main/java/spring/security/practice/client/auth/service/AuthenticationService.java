package spring.security.practice.client.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import spring.security.practice.client.auth.model.AuthenticationReq;
import spring.security.practice.client.auth.model.AuthenticationRes;

import java.util.Optional;

public interface AuthenticationService {
    Optional<AuthenticationRes> authenticate(AuthenticationReq request, HttpServletRequest httpServletRequest);
}
