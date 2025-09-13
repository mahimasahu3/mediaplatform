package com.example.mediaplatform.service;

import com.example.mediaplatform.entity.AdminUser;
import com.example.mediaplatform.repository.AdminUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {

    private final AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(AdminUserRepository adminUserRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String signup(String email, String password) throws Exception {
        Optional<AdminUser> existing = adminUserRepository.findByEmail(email);
        if(existing.isPresent()) throw new Exception("User already exists");
        AdminUser user = new AdminUser(email, passwordEncoder.encode(password), Instant.now());
        adminUserRepository.save(user);
        return jwtService.generateToken(user.getEmail());
    }

    public String login(String email, String password) throws Exception {
        AdminUser user = adminUserRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Invalid credentials"));
        if(!passwordEncoder.matches(password, user.getHashedPassword())) {
            throw new Exception("Invalid credentials");
        }
        return jwtService.generateToken(user.getEmail());
    }
}
