package com.emin.nereye.domain.auth.web;

import com.emin.nereye.domain.user.api.UserDto;
import com.emin.nereye.domain.user.api.UserService;
import com.emin.nereye.security.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = {
                "http://localhost",
                "http://localhost:8080",
                "http://localhost:3000"
        },
        allowCredentials = "true",
        allowedHeaders = {
                "Authorization",
                "Content-Type",
                "X-Requested-With"
        },
        exposedHeaders = {
                "Authorization"
        }
)
public class AuthController {

    private final UserService userService;
    private final JWTService jwtService;

    @Autowired
    public AuthController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.verify(userDto));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(HttpServletRequest request) {
        try {
            // JWT token'ı header'dan al
            String authHeader = request.getHeader("Authorization");
            System.out.println("AuthController - Auth Header: " + authHeader);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                System.out.println("AuthController - Token: " + token);

                // Token'dan kullanıcı ID'sini ve rolünü al
                Integer userId = jwtService.extractUserId(token);
                Integer userRole = jwtService.extractUserRole(token);
                System.out.println("AuthController - User ID from token: " + userId);
                System.out.println("AuthController - User Role from token: " + userRole);

                if (userId != null) {
                    // Kullanıcı bilgilerini veritabanından al
                    UserDto userDto = userService.findById(userId);
                    System.out.println("AuthController - User DTO from DB: " + userDto);

                    // Eğer token'daki rol farklıysa, token'daki rolü kullan
                    if (userRole != null && userDto != null) {
                        userDto.setRole(com.emin.nereye.enumeration.Role.fromValue(userRole));
                        System.out.println("AuthController - Updated role from token: " + userDto.getRole());
                    }

                    return ResponseEntity.ok(userDto);
                }
            }

            System.out.println("AuthController - No valid token found");
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            System.out.println("AuthController - Exception: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
} 