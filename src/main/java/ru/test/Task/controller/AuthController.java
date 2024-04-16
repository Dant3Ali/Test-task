package ru.test.Task.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.test.Task.dao.RoleDao;
import ru.test.Task.dto.UserDto;
import ru.test.Task.entities.ERole;
import ru.test.Task.entities.Role;
import ru.test.Task.entities.User;
import ru.test.Task.jwt.JwtUtils;
import ru.test.Task.mappers.UserMapper;
import ru.test.Task.payload.requests.LoginRequest;
import ru.test.Task.payload.requests.SignupRequest;
import ru.test.Task.payload.responses.LoginResponse;
import ru.test.Task.payload.responses.SignupResponse;
import ru.test.Task.services.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final RoleDao roleService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getLogin());
        System.out.println(loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDto userDetails = (UserDto) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        return ResponseEntity.ok(new LoginResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.findByUserName(signUpRequest.getLogin()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new SignupResponse("Error: Username is already taken!"));
        }

        Set<Role> roles = determineRoles(signUpRequest.getRole());

        if (roles.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new SignupResponse("Error: Role is not found."));
        }

        User user = new User();
        user.setLogin(signUpRequest.getLogin());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRoles(roles);

        userService.createOrUpdateUser(UserMapper.toUserDto(user));

        return ResponseEntity.ok(new SignupResponse("User registered successfully!"));
    }

    private Set<Role> determineRoles(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleService.getRoleByName(ERole.ROLE_USER).orElse(null);
            if (userRole != null) {
                roles.add(userRole);
            }
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.getRoleByName(ERole.ROLE_ADMIN).orElse(null);
                        if (adminRole != null) {
                            roles.add(adminRole);
                        }
                        break;
                    default:
                        Role userRole = roleService.getRoleByName(ERole.ROLE_USER).orElse(null);
                        if (userRole != null) {
                            roles.add(userRole);
                        }
                }
            });
        }

        return roles;
    }

}